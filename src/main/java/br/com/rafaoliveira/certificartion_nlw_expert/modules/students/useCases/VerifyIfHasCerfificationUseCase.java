package br.com.rafaoliveira.certificartion_nlw_expert.modules.students.useCases;

import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.dto.VerifyIfHasCerfificationDTO;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasCerfificationUseCase {
    public boolean execute(VerifyIfHasCerfificationDTO dto){
        return dto.getEmail().equals("rafoliveira217@gmail.com") && dto.getTechnology().equalsIgnoreCase("java");
    }
}
