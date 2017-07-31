package com.qa.consulting.forum.controller;

import com.qa.consulting.forum.dto.EditQuestionDTO;
import com.qa.consulting.forum.dto.QuestionDTO;
import com.qa.consulting.forum.dto.UserViewDTO;
import com.qa.consulting.forum.entities.Answer;
import com.qa.consulting.forum.entities.Question;
import com.qa.consulting.forum.entities.User;
import com.qa.consulting.forum.exception.EntityDoesNotBelongToUserException;
import com.qa.consulting.forum.repository.QuestionRepository;
import com.qa.consulting.forum.repository.UserRepository;
import com.qa.consulting.forum.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Matt on 24/07/2017.
 */
@RestController
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/question/ask", method = RequestMethod.POST)
    public Question askQuestion(@RequestBody QuestionDTO questionDTO) {
        System.out.println("User DTO:");
        System.out.println("userId: " + questionDTO.getUserId());
        System.out.println("title: " + questionDTO.getTitle());
        System.out.println("body: " + questionDTO.getBody());
        User user = userRepository.findOne(questionDTO.getUserId());

        if(user == null) {
            throw new UsernameNotFoundException("User with id " + questionDTO.getUserId() + " not found");
        } else {
            Question question = new Question(questionDTO.getTitle(), questionDTO.getBody(), user);
            return questionRepository.save(question);
        }

    }

    @RequestMapping(value = "/question/{questionId}/author")
    public UserViewDTO getQuestionAuthor(@PathVariable Long questionId) {
        Question question = questionRepository.findOne(questionId);
        User user = question.getUser();
        return new UserViewDTO(user.getId(), user.getUsername(), user.getDisplayName());
    }

    @RequestMapping(value = "/question/{questionId}/updateViewedCount", method = RequestMethod.PATCH)
    public Question updateViewedCount(@PathVariable Long questionId) {
        Question questionToUpdate = questionRepository.findOne(questionId);
        int currentViewedCount = questionToUpdate.getViewedCount();
        questionToUpdate.setViewedCount(currentViewedCount + 1);
        return questionRepository.save(questionToUpdate);
    }

    @RequestMapping(value = "/question/edit", method = RequestMethod.PATCH)
    public Question editQuestion(@RequestBody EditQuestionDTO editQuestionDTO) {
        return questionService.editQuestion(editQuestionDTO);
    }
}
