package com.qa.consulting.forum.config;

import com.qa.consulting.forum.entities.Answer;
import com.qa.consulting.forum.entities.Question;
import com.qa.consulting.forum.entities.User;
import com.qa.consulting.forum.repository.QuestionRepository;
import com.qa.consulting.forum.repository.UserRepository;
import com.qa.consulting.forum.service.AnswerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by Matt on 24/07/2017.
 */
@Configuration
@Profile("demo")
public class DemoConfig {

    /*
	* Add some questions for demo purposes
	* */
    @Bean
    public CommandLineRunner demo(
            QuestionRepository questionRepository,
            UserRepository userRepository,
            AnswerService answerService) {
        return (args) -> {

            User user = new User(
                    "test@qa.com",
                    "Test User",
                    "$2a$06$vObjHaxHZA7vuB4TH.MTH.xYRyKJj.KbrbP88aI3wR2vVEoYgiuam"
            );
            userRepository.save(user);
            Question questionToAdd = new Question("How do I ask a question?",
                    "Hey, I'm just wondering how to ask a queston on the QAC Forum?",
                    user);

            questionRepository.save(questionToAdd);

            Answer answerToAdd = new Answer(user, "This is a tes answer");
            answerService.addAnswer(answerToAdd, questionToAdd);
        };
    }
}
