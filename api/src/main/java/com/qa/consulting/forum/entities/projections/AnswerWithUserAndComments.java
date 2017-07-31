package com.qa.consulting.forum.entities.projections;

import com.qa.consulting.forum.dto.UserViewDTO;
import com.qa.consulting.forum.entities.Answer;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;
import java.util.List;

/**
 * Created by Matt on 27/07/2017.
 */
@Projection(name = "answerWithUserAndComments", types = {Answer.class})
public interface AnswerWithUserAndComments {

    Long getId();
    String getBody();
    UserViewDTO getUserViewDTO();
    int getVotes();
    Date getCreatedAt();
    Date getUpdatedAt();
}
