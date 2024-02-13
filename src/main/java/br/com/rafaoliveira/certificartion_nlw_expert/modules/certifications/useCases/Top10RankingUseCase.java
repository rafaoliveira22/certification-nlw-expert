package br.com.rafaoliveira.certificartion_nlw_expert.modules.certifications.useCases;

import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.entities.CertificationStudentEntity;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.repositories.CertificationStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Top10RankingUseCase {
    @Autowired
    CertificationStudentRepository certificationStudentRepository;

    public List<CertificationStudentEntity> execute(){
        return certificationStudentRepository.findTop10ByOrderByGradeDesc();
    }

}
