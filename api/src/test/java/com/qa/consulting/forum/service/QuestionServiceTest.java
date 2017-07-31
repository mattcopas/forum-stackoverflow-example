package com.qa.consulting.forum.service;

import com.qa.consulting.forum.dto.EditQuestionDTO;
import com.qa.consulting.forum.entities.Question;
import com.qa.consulting.forum.entities.User;
import com.qa.consulting.forum.exception.EntityDoesNotBelongToUserException;
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
 * Created by Matt on 31/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class QuestionServiceTest {

    private static final String TEST_USER_EMAIL = "test@qa.com";
    private static final String TEST_USER_DISPLAY_NAME = "Test User";

    private static final String TEST_QUESTION_TITLE = "Question Title";
    private static final String TEST_QUESTION_BODY = "Question Body";

    private static final String EDITED_TITLE = "Edited Title";
    private static final String EDITED_BODY = "Edited Body";

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    private User user;
    private Question question;

    @Before
    public void setup() {
        user = new User(TEST_USER_EMAIL, TEST_USER_DISPLAY_NAME, "password");
        userRepository.save(user);

        question = new Question(TEST_QUESTION_TITLE, TEST_QUESTION_BODY, user);
        questionRepository.save(question);
    }

    @Test
    public void testEditQuestion() {
        EditQuestionDTO editQuestionDTO = new EditQuestionDTO(question.getId(), user.getId(), EDITED_TITLE, EDITED_BODY);
        Question editedQuestion = questionService.editQuestion(editQuestionDTO);

        Assert.assertEquals("Question title should be updated", EDITED_TITLE, editedQuestion.getTitle());
        Assert.assertEquals("Question body should be updated", EDITED_BODY, editedQuestion.getBody());
        Assert.assertEquals("User id should not be updated", user.getId(), editedQuestion.getUser().getId());
    }

    @Test(expected = EntityDoesNotBelongToUserException.class)
    public void testExceptionThrownIfQuestionDoesNotBelongToUser() {
        EditQuestionDTO editQuestionDTO = new EditQuestionDTO(question.getId(), 1000L, EDITED_TITLE, EDITED_BODY);
        questionService.editQuestion(editQuestionDTO);
    }
}
