package br.com.emiliosanches.certification_nlw.modules.students.useCases;

import org.springframework.stereotype.Service;

import br.com.emiliosanches.certification_nlw.modules.students.dto.StudentHasCertificationRequestDTO;

@Service
public class VerifyStudentHasCertificationUseCase {
  public boolean execute(StudentHasCertificationRequestDTO dto) {
    return false;
  }
}
