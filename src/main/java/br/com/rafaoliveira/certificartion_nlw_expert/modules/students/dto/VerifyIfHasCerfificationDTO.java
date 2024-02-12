package br.com.rafaoliveira.certificartion_nlw_expert.modules.students.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerifyIfHasCerfificationDTO {
    private String email;
    private String technology;
}
