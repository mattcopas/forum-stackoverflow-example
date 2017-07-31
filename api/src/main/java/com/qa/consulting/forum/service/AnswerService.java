package com.qa.consulting.forum.service;

import com.qa.consulting.forum.dto.AnswerDTO;
import com.qa.consulting.forum.dto.EditAnswerDTO;
import com.qa.consulting.forum.entities.Answer;
import com.qa.consulting.forum.entities.Question;
import com.qa.consulting.forum.entities.User;
import com.qa.consulting.forum.exception.EntityDoesNotBelongToUserException;
import com.qa.consulting.forum.repository.AnswerRepository;
import com.qa.consulting.forum.repository.QuestionRepository;
import com.qa.consulting.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Matt on 12/07/2017.
 */
@Service
public class AnswerService {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    public void addAnswer(AnswerDTO answerDTO) {
        Question question = questionRepository.findOne(answerDTO.getQuestionId());
        User user = userRepository.findOne(answerDTO.getUserId());

        Answer answer = new Answer(user, answerDTO.getBody());

        this.addAnswer(answer, question);

    }

    public void addAnswer(Answer answer, Question question) {

        answerRepository.save(answer);

        List<Answer> answers = question.getAnswers();

        answers.add(answer);

        question.setAnswers(answers);

        questionRepository.save(question);
    }

    public Answer editAnswer(EditAnswerDTO editAnswerDTO) {
        Answer answer = answerRepository.findOne(editAnswerDTO.getAnswerId());
        if(editAnswerDTO.getUserId().equals(answer.getUser().getId())) {
            answer.setBody(editAnswerDTO.getBody());
            return answerRepository.save(answer);
        }
        throw new EntityDoesNotBelongToUserException("This answer does not belong to the correct user");
    }
}
