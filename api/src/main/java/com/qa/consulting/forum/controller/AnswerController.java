package com.qa.consulting.forum.controller;

import com.qa.consulting.forum.dto.AnswerDTO;
import com.qa.consulting.forum.dto.EditAnswerDTO;
import com.qa.consulting.forum.entities.Answer;
import com.qa.consulting.forum.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Matt on 26/07/2017.
 */
@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/answer/add", method = RequestMethod.POST)
    public ResponseEntity addAnswer(@RequestBody AnswerDTO answerDTO) {
        answerService.addAnswer(answerDTO);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/answer/edit", method = RequestMethod.PATCH)
    public Answer editAnswer(@RequestBody EditAnswerDTO editAnswerDTO) {
        return answerService.editAnswer(editAnswerDTO);
    }
}
