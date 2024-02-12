package br.com.rafaoliveira.certificartion_nlw_expert.modules.questions.entities;

import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.entities.AnswersCertificationsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "questions")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 50)
    private String technology;

    @Column
    private String description;

    @OneToMany
    @JoinColumn(name = "question_id", insertable = false, updatable = false)
    private List<AlternativesEntity> alternatives;

    @CreationTimestamp
    private LocalDate createdAt;

}
