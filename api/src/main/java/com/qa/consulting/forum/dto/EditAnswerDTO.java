package com.qa.consulting.forum.dto;

/**
 * Created by Matt on 31/07/2017.
 */
public class EditAnswerDTO {

    private Long answerId;

    private Long userId;
    private String body;

    public EditAnswerDTO() {
        // Intentionally left blank
    }

    public EditAnswerDTO(Long answerId, Long userId, String body) {
        this.answerId = answerId;
        this.userId = userId;
        this.body = body;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getBody() {
        return body;
    }
}
