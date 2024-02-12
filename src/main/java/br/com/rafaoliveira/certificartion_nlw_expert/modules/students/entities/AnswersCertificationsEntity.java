package br.com.rafaoliveira.certificartion_nlw_expert.modules.students.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "answers_certification_student")
public class AnswersCertificationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "certification_id", insertable = false, updatable = false)
    private CertificationStudentEntity certificationStudentEntity;
    @Column(name = "certification_id")
    private UUID certificationID;


    @ManyToOne
    @JoinColumn(name = "student_id", insertable = false, updatable = false)
    private StudentEntity studentEntity;
    @Column(name = "student_id")
    private UUID studentID;

    @Column(name = "question_id")
    private UUID questionID;

    @Column(name = "answer_id")
    private UUID answerID;

    @CreationTimestamp
    private LocalDate createdAt;
}
