package com.qa.consulting.forum.entities;

import javax.persistence.Entity;

/**
 * Created by Matt on 12/07/2017.
 */
@Entity
public class Tag extends BaseEntity {

    public Tag() {
        // Required for Hibernate
    }

    private String tag;

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }
}
