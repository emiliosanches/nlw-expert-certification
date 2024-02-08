package br.com.emiliosanches.certification_nlw.modules.students.useCases;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emiliosanches.certification_nlw.modules.questions.repositories.QuestionsRepository;
import br.com.emiliosanches.certification_nlw.modules.students.dto.CertifyStudentDTO;
import br.com.emiliosanches.certification_nlw.modules.students.dto.StudentHasCertificationRequestDTO;
import br.com.emiliosanches.certification_nlw.modules.students.entities.CertificationAnswersEntity;
import br.com.emiliosanches.certification_nlw.modules.students.entities.StudentCertificationEntity;
import br.com.emiliosanches.certification_nlw.modules.students.entities.StudentEntity;
import br.com.emiliosanches.certification_nlw.modules.students.repositories.StudentCertificationRepository;
import br.com.emiliosanches.certification_nlw.modules.students.repositories.StudentRepository;

@Service
public class CertifyStudentUseCase {
  @Autowired
  private StudentRepository studentRepository;

  @Autowired
  private QuestionsRepository questionsRepository;

  @Autowired
  private StudentCertificationRepository studentCertificationRepository;

  @Autowired
  private VerifyStudentHasCertificationUseCase verifyStudentHasCertificationUseCase;

  public StudentCertificationEntity execute(CertifyStudentDTO certifyStudentDTO) throws Exception {
    var studentHasCertification = this.verifyStudentHasCertificationUseCase.execute(
        new StudentHasCertificationRequestDTO(certifyStudentDTO.getEmail(), certifyStudentDTO.getTechnology()));

    if (studentHasCertification)
      throw new Exception("User is already certified");

    var questionsEntity = this.questionsRepository.findByTechnology(certifyStudentDTO.getTechnology());

    List<CertificationAnswersEntity> certificationAnswers = new ArrayList<>();

    AtomicInteger correctAnswers = new AtomicInteger(0);

    certifyStudentDTO.getQuestionsAnswers().stream().forEach(questionAnswer -> {
      var question = questionsEntity.stream()
          .filter(q -> q.getId().equals(questionAnswer.getQuestionId())).findFirst().get();

      var correctAlternative = question.getAlternatives().stream().filter(alternative -> alternative.isCorrect())
          .findFirst().get();

      if (correctAlternative.getId().equals(questionAnswer.getAlternativeId())) {
        questionAnswer.setCorrect(true);
        correctAnswers.incrementAndGet();
      } else {
        questionAnswer.setCorrect(false);
      }

      var certificationAnswer = CertificationAnswersEntity.builder().answerId(questionAnswer.getAlternativeId())
          .questionId(questionAnswer.getQuestionId()).isCorrect(questionAnswer.isCorrect()).build();

      certificationAnswers.add(certificationAnswer);
    });

    var student = this.studentRepository.findByEmail(certifyStudentDTO.getEmail());
    UUID studentId;

    if (student.isEmpty()) {
      StudentEntity studentCreated = StudentEntity.builder().email(certifyStudentDTO.getEmail()).build();

      studentCreated = this.studentRepository.save(studentCreated);
      studentId = studentCreated.getId();
    } else {
      studentId = student.get().getId();
    }

    StudentCertificationEntity studentCertificationEntity = StudentCertificationEntity.builder()
        .technology(certifyStudentDTO.getTechnology())
        .studentId(studentId)
        .grade(correctAnswers.get())
        .build();

    var savedStudentCertification = this.studentCertificationRepository.save(studentCertificationEntity);

    certificationAnswers.forEach(certificationAnswer -> {
      certificationAnswer.setCertificationId(savedStudentCertification.getId());
      certificationAnswer.setStudentCertificationEntity(savedStudentCertification);
      certificationAnswer.setStudentId(studentId);
    });

    savedStudentCertification.setCertificationAnswersEntity(certificationAnswers);

    this.studentCertificationRepository.save(savedStudentCertification);

    return savedStudentCertification;
  }
}
