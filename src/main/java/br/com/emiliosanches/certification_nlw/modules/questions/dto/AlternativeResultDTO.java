package br.com.emiliosanches.certification_nlw.modules.questions.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlternativeResultDTO {
  private UUID id;
  private String description;
}
