package br.com.rafaoliveira.certificartion_nlw_expert.modules.students.controllers;

import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.dto.VerifyIfHasCerfificationDTO;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.useCases.VerifyIfHasCerfificationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudenController {
    @Autowired
    public VerifyIfHasCerfificationUseCase verifyIfHasCerfificationUseCase;
    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyIfHasCerfificationDTO verifyIfHasCerfificationDTO) {
        if(verifyIfHasCerfificationUseCase.execute(verifyIfHasCerfificationDTO)){
            return "Usuário já fez a prova!!!";
        }

        return "Usuário pode fazer a prova!!!";
    }

}
