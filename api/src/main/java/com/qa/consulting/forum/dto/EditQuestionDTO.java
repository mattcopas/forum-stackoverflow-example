package com.qa.consulting.forum.dto;

/**
 * Created by Matt on 31/07/2017.
 */
public class EditQuestionDTO {

    private Long questionId;
    private Long userId;
    private String editedTitle;
    private String editedBody;

    public EditQuestionDTO() {

    }

    public EditQuestionDTO(Long questionId, Long userId, String editedTitle, String editedBody) {
        this.questionId = questionId;
        this.userId = userId;
        this.editedTitle = editedTitle;
        this.editedBody = editedBody;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getEditedTitle() {
        return editedTitle;
    }

    public String getEditedBody() {
        return editedBody;
    }

}
