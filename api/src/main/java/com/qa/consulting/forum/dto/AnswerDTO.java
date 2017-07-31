package com.qa.consulting.forum.dto;

/**
 * Created by Matt on 26/07/2017.
 */
public class AnswerDTO {

    private String body;
    private Long userId;
    private Long questionId;

    public AnswerDTO() {

    }

    public AnswerDTO(String body, Long userId, Long questionId) {
        this.body = body;
        this.userId = userId;
        this.questionId = questionId;
    }

    public String getBody() {
        return body;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getQuestionId() {
        return questionId;
    }

}
