package br.com.emiliosanches.certification_nlw.modules.ranking.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.emiliosanches.certification_nlw.modules.students.entities.StudentCertificationEntity;
import br.com.emiliosanches.certification_nlw.modules.students.repositories.StudentCertificationRepository;

@Service
public class Top10RankingUseCase {
  @Autowired
  StudentCertificationRepository studentCertificationRepository;

  public List<StudentCertificationEntity> execute() {
    var result = this.studentCertificationRepository.findTop10ByOrderByGradeDesc();

    return result;
  }
}
