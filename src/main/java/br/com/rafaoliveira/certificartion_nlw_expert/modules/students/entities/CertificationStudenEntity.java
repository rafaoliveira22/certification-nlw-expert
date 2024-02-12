package br.com.rafaoliveira.certificartion_nlw_expert.modules.students.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CertificationStudenEntity {
    private UUID id;
    private UUID studentID;
    private String technology;
    private int grade;
    private List<AnswersCertificationsEntity> answersCertificationsEntity;

}
