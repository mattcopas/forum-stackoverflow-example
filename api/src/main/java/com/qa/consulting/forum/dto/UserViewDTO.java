package com.qa.consulting.forum.dto;

/**
 * Created by Matt on 24/07/2017.
 */
public class UserViewDTO {

    private String email;
    private Long id;
    private String displayName;

    public UserViewDTO(Long id, String email, String displayName) {
        this.id = id;
        this.email = email;
        this.displayName = displayName;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}
