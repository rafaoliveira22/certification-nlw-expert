package br.com.rafaoliveira.certificartion_nlw_expert.modules.questions.repositories;

import br.com.rafaoliveira.certificartion_nlw_expert.modules.questions.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<QuestionEntity, UUID> {
    List<QuestionEntity> findByTechnology(String technology);
}
