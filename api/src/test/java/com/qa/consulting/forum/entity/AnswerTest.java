package com.qa.consulting.forum.entity;

import com.qa.consulting.forum.dto.UserViewDTO;
import com.qa.consulting.forum.entities.Answer;
import com.qa.consulting.forum.entities.User;
import com.qa.consulting.forum.repository.AnswerRepository;
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
public class AnswerTest {

    public static final String TEST_USER_EMAIL = "test@qa.com";
    public static final String TEST_USER_DISPLAY_NAME = "Test User";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;

    private Answer answer;
    private User user;

    @Before
    public void setup() {
        user = new User(TEST_USER_EMAIL, TEST_USER_DISPLAY_NAME, "password");
        User savedUser = userRepository.save(user);
        answer = answerRepository.save(new Answer(savedUser, "Test Answer"));
    }

    @Test
    public void testGetAnswersUserViewDTO() {
        UserViewDTO userViewDTO = answer.getUserViewDTO();
        Assert.assertNotNull(userViewDTO);
        Assert.assertEquals(TEST_USER_EMAIL, userViewDTO.getEmail());
        Assert.assertEquals(TEST_USER_DISPLAY_NAME, userViewDTO.getDisplayName());
        Assert.assertEquals(user.getId(), userViewDTO.getId());

    }
}
