package com.project.application.service;

import com.project.application.entity.Authority;
import com.project.application.entity.User;

import java.util.List;

public interface AuthorityService
{
    public void saveAuthority(Authority authority);

    public List<Authority> getAllAuthorities();

    public List<Authority> getByIdUsername(User user);

    public List<Authority> getByIdAuthority(String authority);

    public Authority getByIdUsernameAndAuthority(User user, String authority);

    public Authority getIfManagerAuthority(User user);

    public void removeAuthority(Authority authority);

    public void makeManagerAuthority(User user);

    public void removeManagerAuthority(User user);

    public void deleteAllAuthoritiesOf(User user);
}
