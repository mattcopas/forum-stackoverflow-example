package com.qa.consulting.forum.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Matt on 12/07/2017.
 */
@Entity
public class User extends BaseEntity implements UserDetails {

    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String displayName;
    private String password;
    private boolean enabled;
    private boolean locked;

    public User() {
        // Intentionally left blank
    }

    public User(String username, String displayName, String password) {
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.enabled = true;
        this.locked = false;
    }

    public User(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }
}
