package com.qa.consulting.forum.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Matt on 11/07/2017.
 */
@Entity
public class BaseEntity {

    @Id
    @GeneratedValue
    private Long Id;

    private Date createdAt;
    private Date updatedAt;

    public Long getId() {
        return this.Id;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = new Date();
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

}
