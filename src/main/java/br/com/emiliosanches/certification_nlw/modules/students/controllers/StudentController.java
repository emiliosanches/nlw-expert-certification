package br.com.emiliosanches.certification_nlw.modules.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.emiliosanches.certification_nlw.modules.students.dto.CertifyStudentDTO;
import br.com.emiliosanches.certification_nlw.modules.students.dto.StudentHasCertificationRequestDTO;
import br.com.emiliosanches.certification_nlw.modules.students.useCases.CertifyStudentUseCase;
import br.com.emiliosanches.certification_nlw.modules.students.useCases.VerifyStudentHasCertificationUseCase;

@RestController
@RequestMapping("students")
public class StudentController {
  @Autowired
  private VerifyStudentHasCertificationUseCase verifyStudentHasCertificationUseCase;

  @Autowired
  private CertifyStudentUseCase certifyStudentUseCase;

  @PostMapping("hasCertification")
  public String studentHasCertification(
      @RequestBody StudentHasCertificationRequestDTO body) {
    var result = this.verifyStudentHasCertificationUseCase.execute(body);

    if (result)
      return "User is already certified";

    return "User can get certified";
  }

  @PostMapping("certifications")
  public ResponseEntity<Object> studentCertificationAnswer(@RequestBody CertifyStudentDTO certifyStudentDTO) {
    try {
      return ResponseEntity.ok(this.certifyStudentUseCase.execute(certifyStudentDTO));
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
