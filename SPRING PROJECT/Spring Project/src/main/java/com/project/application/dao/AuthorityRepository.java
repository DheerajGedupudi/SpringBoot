package com.project.application.dao;

import com.project.application.entity.Authority;
import com.project.application.entity.User;
import com.project.application.entity.id.AuthorityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, AuthorityId>
{
    public List<Authority> findByAuthorityIdUsername(User user);

    public List<Authority> findByAuthorityIdAuthority(String authority);

    public Authority findByAuthorityId(AuthorityId authorityId);
}
