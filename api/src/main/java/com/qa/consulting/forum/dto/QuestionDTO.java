package com.qa.consulting.forum.dto;

/**
 * Created by Matt on 24/07/2017.
 */
public class QuestionDTO {

    private Long userId;
    private String title;
    private String body;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
