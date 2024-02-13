package br.com.rafaoliveira.certificartion_nlw_expert.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCertificationAnswerDTO {
    private String email;
    private String technology;
    private List<QuestionAnswerDTO> questionAnswer;
}
