package br.com.rafaoliveira.certificartion_nlw_expert.modules.students.repositories;

import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID> {
    public Optional<StudentEntity> findByEmail(String email);
}
