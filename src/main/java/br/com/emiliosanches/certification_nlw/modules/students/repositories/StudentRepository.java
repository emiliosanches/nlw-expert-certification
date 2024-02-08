package br.com.emiliosanches.certification_nlw.modules.students.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.emiliosanches.certification_nlw.modules.students.entities.StudentEntity;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
  public Optional<StudentEntity> findByEmail(String email);
}
