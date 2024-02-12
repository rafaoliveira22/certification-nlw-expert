package br.com.rafaoliveira.certificartion_nlw_expert.modules.students.useCases;

import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.dto.VerifyIfHasCerfificationDTO;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerifyIfHasCertificationUseCase {
    @Autowired
    CertificationStudentRepository certificationStudentRepository;
    public boolean execute(VerifyIfHasCerfificationDTO dto){
        var result = certificationStudentRepository.findStudentsByEmailAndTechnology(dto.getEmail(), dto.getTechnology());
        return !(result.isEmpty());
    }
}
