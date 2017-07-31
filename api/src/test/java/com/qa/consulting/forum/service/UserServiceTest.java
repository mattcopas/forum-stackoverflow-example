package com.qa.consulting.forum.service;


import com.qa.consulting.forum.entities.User;
import com.qa.consulting.forum.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Matt on 27/07/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceTest {

    public static final String TEST_USER_EMAIL = "test@qa.com";
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private User user;

    @Before
    public void setup() {
        user = userRepository.save(new User(TEST_USER_EMAIL));
    }

    @Test
    public void testLoadUserByUsername() {
        UserDetails foundUser = userService.loadUserByUsername(TEST_USER_EMAIL);
        Assert.assertNotNull(foundUser);
        Assert.assertEquals(TEST_USER_EMAIL, foundUser.getUsername());

    }
}
