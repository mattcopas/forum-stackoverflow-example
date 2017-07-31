package com.qa.consulting.forum.entities.projections;

import com.qa.consulting.forum.dto.UserViewDTO;
import com.qa.consulting.forum.entities.Question;
import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

/**
 * Created by Matt on 27/07/2017.
 */
@Projection(name = "withUserAndNumberOfAnswers", types = {Question.class})
public interface QuestionWithNumberOfAnswers {

    Long getId();
    String getTitle();
    String getBody();
    UserViewDTO getUserViewDTO();
    int getNumberOfAnswers();
    int getVotes();
    Date getCreatedAt();
    Date getUpdatedAt();
}
