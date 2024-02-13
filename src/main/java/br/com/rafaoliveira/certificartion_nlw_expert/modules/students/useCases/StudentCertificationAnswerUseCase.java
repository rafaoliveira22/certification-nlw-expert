package br.com.rafaoliveira.certificartion_nlw_expert.modules.students.useCases;

import br.com.rafaoliveira.certificartion_nlw_expert.modules.questions.entities.QuestionEntity;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.questions.repositories.QuestionRepository;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.dto.StudentCertificationAnswerDTO;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.dto.VerifyIfHasCerfificationDTO;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.entities.AnswersCertificationsEntity;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.entities.CertificationStudentEntity;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.entities.StudentEntity;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.repositories.CertificationStudentRepository;
import br.com.rafaoliveira.certificartion_nlw_expert.modules.students.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StudentCertificationAnswerUseCase {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private CertificationStudentRepository certificationStudentRepository;

    @Autowired
    private VerifyIfHasCertificationUseCase verifyIfHasCertificationUseCase;

    public CertificationStudentEntity execute(StudentCertificationAnswerDTO dto) throws Exception{
        var hasCertification = verifyIfHasCertificationUseCase.execute(new VerifyIfHasCerfificationDTO(dto.getEmail(), dto.getTechnology()));
        if(!hasCertification){
            throw new Exception("You already obtained this certification");
        }

        List<QuestionEntity> questionsEntity = questionRepository.findByTechnology(dto.getTechnology());
        List<AnswersCertificationsEntity> answersCertifications = new ArrayList<AnswersCertificationsEntity>();
        AtomicInteger correctAnswers = new AtomicInteger(0);

        dto.getQuestionAnswer().stream().forEach(questionAnswer -> {
            var question = questionsEntity.stream()
                           .filter(q -> q.getId().equals(questionAnswer.getQuestionID()))
                           .findFirst().get();

            var findCorrectAlternative = question.getAlternatives().stream()
                                        .filter(alternative -> alternative.isCorrect())
                                        .findFirst().get();

            if(findCorrectAlternative.getId().equals(questionAnswer.getAlternativeID())){
                questionAnswer.setCorrect(true);
                correctAnswers.incrementAndGet();
            } else{
                questionAnswer.setCorrect(false);
            }

            var answersCertificationsEntity = AnswersCertificationsEntity.builder()
            .questionID(questionAnswer.getQuestionID())
            .answerID(questionAnswer.getAlternativeID())
            .isCorrect(questionAnswer.isCorrect())
            .build();

            answersCertifications.add(answersCertificationsEntity);
        });

        var student  = studentRepository.findByEmail(dto.getEmail());
        UUID studentID;
        if(student.isEmpty()){
            var studentCreated = StudentEntity.builder().email(dto.getEmail()).build();
            studentCreated = studentRepository.save(studentCreated);
            studentID = studentCreated.getId();
        } else{
            studentID = student.get().getId();
        }

        CertificationStudentEntity certificationStudentEntity =
        CertificationStudentEntity.builder()
        .technology(dto.getTechnology())
        .studentID(studentID)
        .grade(correctAnswers.get())
        .build();

        var certificationStudentCreated  = certificationStudentRepository.save(certificationStudentEntity);

        answersCertifications.stream().forEach(answerCertification -> {
            answerCertification.setCertificationID(certificationStudentEntity.getId());
            answerCertification.setCertificationStudentEntity(certificationStudentEntity);
        });
        certificationStudentEntity.setAnswersCertificationsEntity(answersCertifications);
        return certificationStudentRepository.save(certificationStudentEntity);
    }

}
