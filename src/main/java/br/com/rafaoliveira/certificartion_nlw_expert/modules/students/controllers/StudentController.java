package br.com.rafaoliveira.certificartion_nlw_expert.modules.students.controllers;

import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.dto.VerifyIfHasCerfificationDTO;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.entities.CertificationStudentEntity;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.useCases.StudentCertificationAnswerUseCase;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.useCases.VerifyIfHasCertificationUseCase;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    public VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    @Autowired
    public StudentCertificationAnswerUseCase studentCertificationAnswerUseCase;
    @PostMapping("/verifyIfHasCertification")
    public String verifyIfHasCertification(@RequestBody VerifyIfHasCerfificationDTO verifyIfHasCerfificationDTO) {
        if(!verifyIfHasCertificationUseCase.execute(verifyIfHasCerfificationDTO)){
            return "You already take this test!!!";
        }

        return "You can take this test!!!";
    }

    @PostMapping("/certification/answer")
    public ResponseEntity<Object> certificationAnswer(@RequestBody  StudentCertificationAnswerDTO studentCertificationAnswerDTO) {
        try{
            var result  = studentCertificationAnswerUseCase.execute(studentCertificationAnswerDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
