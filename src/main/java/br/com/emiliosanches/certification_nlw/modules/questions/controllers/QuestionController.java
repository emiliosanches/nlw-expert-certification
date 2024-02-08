package br.com.emiliosanches.certification_nlw.modules.questions.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.emiliosanches.certification_nlw.modules.questions.dto.AlternativeResultDTO;
import br.com.emiliosanches.certification_nlw.modules.questions.dto.QuestionResultDTO;
import br.com.emiliosanches.certification_nlw.modules.questions.entities.AlternativeEntity;
import br.com.emiliosanches.certification_nlw.modules.questions.entities.QuestionEntity;
import br.com.emiliosanches.certification_nlw.modules.questions.repositories.QuestionsRepository;

@RestController
@RequestMapping("questions")
public class QuestionController {
  @Autowired
  QuestionsRepository questionsRepository;

  @GetMapping("technology/{technology}")
  public List<QuestionResultDTO> findByTechnology(
      @PathVariable String technology) {
    var result = this.questionsRepository.findByTechnology(technology);

    var mappedResult = result.stream().map(question -> mapQuestionToDTO(question)).collect(Collectors.toList());

    return mappedResult;
  }

  static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
    var questionDTO = QuestionResultDTO.builder()
        .id(question.getId())
        .description(question.getDescription())
        .technology(question.getTechnology())
        .build();

    List<AlternativeResultDTO> alternativesDTOs = question.getAlternatives().stream()
        .map(alternative -> mapAlternativeToDTO(alternative)).collect(Collectors.toList());

    questionDTO.setAlternatives(alternativesDTOs);

    return questionDTO;
  }

  static AlternativeResultDTO mapAlternativeToDTO(AlternativeEntity alternative) {
    return AlternativeResultDTO.builder().description(alternative.getDescription()).id(alternative.getId()).build();
  }
}
