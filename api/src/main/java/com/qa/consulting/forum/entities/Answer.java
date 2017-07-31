package com.qa.consulting.forum.entities;

import com.qa.consulting.forum.dto.UserViewDTO;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 11/07/2017.
 */
@Entity
public class Answer extends BaseEntity {

    public Answer() {
        // Required by Hibernate
    }

    public Answer(User user, String body) {
        this.user = user;
        this.body = body;
        this.votes = 0;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @Column(columnDefinition = "text")
    private String body;

    private int votes;

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }

    public User getUser() {
        return this.user;
    }

    public int getVotes() {
        return this.votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public UserViewDTO getUserViewDTO() {
        return new UserViewDTO(this.user.getId(), this.user.getUsername(), this.user.getDisplayName());
    }
}
