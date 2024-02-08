package br.com.emiliosanches.certification_nlw.modules.students.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.emiliosanches.certification_nlw.modules.students.entities.StudentCertificationEntity;

public interface StudentCertificationRepository extends JpaRepository<StudentCertificationEntity, UUID> {
  @Query("SELECT c FROM certifications c INNER JOIN c.studentEntity std WHERE std.email = :email AND c.technology = :technology")
  List<StudentCertificationEntity> findByStudentEmailAndTechnology(String email, String technology);

  List<StudentCertificationEntity> findTop10ByOrderByGradeDesc();
}
