package com.qa.consulting.forum.controller;

import com.qa.consulting.forum.exception.EmailNotValidException;
import com.qa.consulting.forum.exception.UserExistsException;
import com.qa.consulting.forum.dto.UserViewDTO;
import com.qa.consulting.forum.entities.User;
import com.qa.consulting.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * Created by Matt on 24/07/2017.
 */
@RestController
public class UserController implements Serializable {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserViewDTO getUser(@RequestParam String email) {
        User user = userRepository.findOneByUsername(email);
        UserViewDTO userViewDTO = new UserViewDTO(user.getId(), user.getUsername(), user.getDisplayName());
        return userViewDTO;

    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<String> registerUser(@RequestBody User user) {

        if (userRepository.findOneByUsername(user.getUsername()) != null) {
            return new ResponseEntity<String>(
                    "User with email " + user.getUsername() + " already exists",
                    HttpStatus.FOUND);
        }

        if(user.getUsername().toLowerCase().endsWith("@qa.com")) {

            String encryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            User userToSave = new User(user.getUsername(), user.getDisplayName(), encryptedPassword);
            userRepository.save(userToSave);
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        } else {
            throw new EmailNotValidException(
                    "The email address registered must be a QA email address"
            );
        }

    }
}
