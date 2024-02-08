package br.com.emiliosanches.certification_nlw.modules.students.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emiliosanches.certification_nlw.modules.students.dto.StudentHasCertificationRequestDTO;
import br.com.emiliosanches.certification_nlw.modules.students.repositories.StudentCertificationRepository;

@Service
public class VerifyStudentHasCertificationUseCase {
  @Autowired
  private StudentCertificationRepository certificationRepository;

  public boolean execute(StudentHasCertificationRequestDTO dto) {
    var result = this.certificationRepository.findByStudentEmailAndTechnology(dto.getEmail(), dto.getTechnology());

    return !result.isEmpty();
  }
}
