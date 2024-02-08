package br.com.emiliosanches.certification_nlw.modules.students.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "certifications")
public class StudentCertificationEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "student_id")
  private UUID studentId;

  @Column(length = 100)
  private String technology;

  @Column(length = 10)
  private int grade;

  @ManyToOne
  @JoinColumn(name = "student_id", insertable = false, updatable = false)
  @JsonManagedReference
  private StudentEntity studentEntity;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "certification_id", insertable = false, updatable = false)
  @JsonBackReference
  private List<CertificationAnswersEntity> certificationAnswersEntity;

  @CreationTimestamp
  private LocalDateTime createdAt;
}
