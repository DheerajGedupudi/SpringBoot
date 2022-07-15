package com.project.application.service;

import com.project.application.dao.AuthorityRepository;
import com.project.application.entity.Authority;
import com.project.application.entity.User;
import com.project.application.entity.id.AuthorityId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorityServiceImplementation implements AuthorityService
{
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public void saveAuthority(Authority authority)
    {
        authorityRepository.save(authority);
    }


    @Override
    public List<Authority> getAllAuthorities()
    {
        return authorityRepository.findAll();
    }

    @Override
    public List<Authority> getByIdUsername(User user) {
        return authorityRepository.findByAuthorityIdUsername(user);
    }

    @Override
    public List<Authority> getByIdAuthority(String authority) {
        return authorityRepository.findByAuthorityIdAuthority(authority);
    }

    @Override
    public Authority getByIdUsernameAndAuthority(User user, String authority)
    {
        AuthorityId myAuthorityId = new AuthorityId(user, authority);
        return authorityRepository.findByAuthorityId(myAuthorityId);
    }

    @Override
    public Authority getIfManagerAuthority(User user)
    {
        String managerAuthority = "ROLE_MANAGER";
        return getByIdUsernameAndAuthority(user, managerAuthority);
    }

    @Override
    public void removeAuthority(Authority authority) {
        authorityRepository.delete(authority);
    }

    @Override
    public void makeManagerAuthority(User user)
    {
        String managerAuthority = "ROLE_MANAGER";

        authorityRepository.save(new Authority(new AuthorityId(user, managerAuthority)));
    }

    @Override
    public void removeManagerAuthority(User user)
    {
        Authority managerAuthority = getIfManagerAuthority(user);
        if (managerAuthority!=null)
        {
            removeAuthority(managerAuthority);
        }

    }

    @Override
    public void deleteAllAuthoritiesOf(User user)
    {
        List<Authority> authorityList = getByIdUsername(user);

        authorityRepository.deleteAll(authorityList);
    }

}
