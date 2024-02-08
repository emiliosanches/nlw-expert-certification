package br.com.emiliosanches.certification_nlw.modules.questions.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emiliosanches.certification_nlw.modules.questions.entities.QuestionEntity;

public interface QuestionsRepository extends JpaRepository<QuestionEntity, UUID> {
  List<QuestionEntity> findByTechnology(String technology);
}
