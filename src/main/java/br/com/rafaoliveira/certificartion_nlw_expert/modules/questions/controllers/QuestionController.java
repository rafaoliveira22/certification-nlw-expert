package br.com.rafaoliveira.certificartion_nlw_expert.modules.questions.controllers;

import br.com.rafaoliveira.certificartion_nlw_expert.modules.questions.dto.AlternativesResultDTO;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.questions.dto.QuestionResultDTO;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.questions.entities.AlternativesEntity;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.questions.entities.QuestionEntity;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.questions.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    QuestionRepository questionRepository;

    @GetMapping("/technology/{technology}")
    public List<QuestionResultDTO> findByTechnology(@PathVariable String technology) {
        var result = this.questionRepository.findByTechnology(technology);

        return result.stream().map(question -> mapQuestionToDTO(question))
                .collect(Collectors.toList());
    }

    static QuestionResultDTO mapQuestionToDTO(QuestionEntity question) {
        var questionResultDTO = QuestionResultDTO.builder()
                .id(question.getId())
                .technology(question.getTechnology())
                .description(question.getDescription()).build();

        List<AlternativesResultDTO> alternativesResultDTOs = question.getAlternatives()
                .stream().map(alternative -> mapAlternativeToDTO(alternative))
                .collect(Collectors.toList());

        questionResultDTO.setAlternatives(alternativesResultDTOs);
        return questionResultDTO;
    }

    static AlternativesResultDTO mapAlternativeToDTO(AlternativesEntity alternativesResultDTO) {
        return AlternativesResultDTO.builder()
                .id(alternativesResultDTO.getId())
                .description(alternativesResultDTO.getDescription()).build();
    }

}
