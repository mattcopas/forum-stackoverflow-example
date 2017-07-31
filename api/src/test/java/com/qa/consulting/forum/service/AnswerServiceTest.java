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
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Matt on 27/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AnswerServiceTest {

    private static final String TEST_ANSWER_BODY = "Test Answer Body";

    private static final String EDITED_ANSWER_BODY = "Edited Answer";

    @Autowired
    private AnswerService answerService;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    private AnswerDTO answerDTO;
    private User user;

    @Before
    public void setup() {
        user = userRepository.save(new User("test@qa.com"));
        Question question = questionRepository.save(new Question("Test Question Title", "Test Question Body", user));
        answerDTO = new AnswerDTO(TEST_ANSWER_BODY, user.getId(), question.getId());
        answerService.addAnswer(answerDTO);
    }

    @Test
    public void addAnswerUsingAnswerDTO() {
        Answer foundAnswer = answerRepository.findAll().iterator().next();
        Assert.assertNotNull(foundAnswer);
        Assert.assertEquals(TEST_ANSWER_BODY, foundAnswer.getBody());
    }

    @Test
    public void testEditAnswer() {
        Answer originalAnswer = answerRepository.findAll().iterator().next();
        EditAnswerDTO editAnswerDTO = new EditAnswerDTO(originalAnswer.getId(), user.getId(), EDITED_ANSWER_BODY);
        Answer editedAnswer = answerService.editAnswer(editAnswerDTO);
        Assert.assertEquals("The answer body should update", EDITED_ANSWER_BODY, editedAnswer.getBody());
        Assert.assertEquals("The userId should not update", user.getId(), editedAnswer.getUser().getId());
    }

    @Test(expected = EntityDoesNotBelongToUserException.class)
    public void testExceptionIsThrownIfTheAnswerDoesNotBelongToTheUserProvidedInTheDTO() {
        Answer originalAnswer = answerRepository.findAll().iterator().next();
        EditAnswerDTO editAnswerDTO = new EditAnswerDTO(originalAnswer.getId(), 10000L, EDITED_ANSWER_BODY);
        answerService.editAnswer(editAnswerDTO);
    }
}
