package com.qa.consulting.forum.service;

import com.qa.consulting.forum.dto.EditQuestionDTO;
import com.qa.consulting.forum.entities.Question;
import com.qa.consulting.forum.exception.EntityDoesNotBelongToUserException;
import com.qa.consulting.forum.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Matt on 31/07/2017.
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question editQuestion(EditQuestionDTO editQuestionDTO) {
        Question questionToEdit = questionRepository.findOne(editQuestionDTO.getQuestionId());
        if(questionToEdit.getUser().getId().equals(editQuestionDTO.getUserId())) {
            questionToEdit.setTitle(editQuestionDTO.getEditedTitle());
            questionToEdit.setBody(editQuestionDTO.getEditedBody());
            return questionRepository.save(questionToEdit);
        }
        throw new EntityDoesNotBelongToUserException("The question to edit does not belong to the correct user");
    }
}
