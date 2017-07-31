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
public class Question extends BaseEntity {

    private String title;
    @Column(columnDefinition = "text")
    private String body;
    private int votes;
    private int viewedCount;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers;

    public Question() {
        // Required by Hibernate
    }

    public Question(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.votes = 0;
        this.viewedCount = 0;
        this.answers = new ArrayList<>();

    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return this.body;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public int getVotes() {
        return this.votes;
    }

    public void setViewedCount(int viewedCount) {
        this.viewedCount = viewedCount;
    }

    public int getViewedCount() {
        return this.viewedCount;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public User getUser() {
        return user;
    }

    public UserViewDTO getUserViewDTO() {
        return new UserViewDTO(user.getId(), user.getUsername(), user.getDisplayName());
    }

    public int getNumberOfAnswers() {
        return this.answers.size();
    }




}
