package com.project.application.entity;

import com.project.application.entity.id.AuthorityId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authority implements Serializable
{

    @EmbeddedId
    private AuthorityId authorityId;

    public Authority() {
    }

    public Authority(AuthorityId authorityId) {
        this.authorityId = authorityId;
    }

    public AuthorityId getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(AuthorityId authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "authorityId=" + authorityId +
                '}';
    }
}
