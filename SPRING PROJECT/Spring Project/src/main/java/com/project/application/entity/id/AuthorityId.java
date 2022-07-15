package com.project.application.entity.id;

import com.project.application.entity.User;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;


@Embeddable
public class AuthorityId implements Serializable
{
    @ManyToOne
    @JoinColumn(name = "username")
    private User username;

    @Column(name = "authority")
    private String authority;

    public AuthorityId() {
    }

    public AuthorityId(User username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "AuthorityId{" +
                "username=" + username +
                ", authority='" + authority + '\'' +
                '}';
    }
}
