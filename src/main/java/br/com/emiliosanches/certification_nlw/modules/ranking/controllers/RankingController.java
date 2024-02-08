package br.com.emiliosanches.certification_nlw.modules.ranking.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.emiliosanches.certification_nlw.modules.ranking.useCases.Top10RankingUseCase;
import br.com.emiliosanches.certification_nlw.modules.students.entities.StudentCertificationEntity;

@RestController
@RequestMapping("ranking")
public class RankingController {
  @Autowired
  private Top10RankingUseCase top10RankingUseCase;

  @GetMapping
  public List<StudentCertificationEntity> top10() {
    return this.top10RankingUseCase.execute();
  }
}
