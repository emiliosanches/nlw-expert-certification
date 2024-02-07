package br.com.emiliosanches.certification_nlw.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.emiliosanches.certification_nlw.modules.students.dto.StudentHasCertificationRequestDTO;
import br.com.emiliosanches.certification_nlw.modules.students.useCases.VerifyStudentHasCertificationUseCase;

@RestController
@RequestMapping("students")
public class StudentController {
  @Autowired
  VerifyStudentHasCertificationUseCase verifyStudentHasCertificationUseCase;

  @PostMapping("hasCertification")
  public String studentHasCertification(@RequestBody StudentHasCertificationRequestDTO body) {
    var result = this.verifyStudentHasCertificationUseCase.execute(body);

    if (result)
      return "Usuário já fez a prova";

    return "Usuário pode fazer a prova";
  }
}
