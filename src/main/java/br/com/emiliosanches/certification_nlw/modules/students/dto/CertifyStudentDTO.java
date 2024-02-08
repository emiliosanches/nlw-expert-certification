package br.com.emiliosanches.certification_nlw.modules.students.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CertifyStudentDTO {
  private String email;
  private String technology;
  private List<QuestionAnswerDTO> questionsAnswers;
}
