package br.com.rafaoliveira.certificartion_nlw_expert.modules.students.repositories;

import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.entities.CertificationStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CertificationStudentRepository extends JpaRepository<CertificationStudentEntity, UUID> {

    @Query("SELECT c FROM certifications c INNER JOIN c.studentEntity std WHERE std.email=:email AND c.technology=:technology")
    List<CertificationStudentEntity> findStudentsByEmailAndTechnology(String email,String technology);
}
