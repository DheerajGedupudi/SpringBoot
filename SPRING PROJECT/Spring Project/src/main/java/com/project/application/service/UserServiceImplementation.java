package com.project.application.service;

import com.project.application.dao.UserRepository;
import com.project.application.entity.Authority;
import com.project.application.entity.User;
import com.project.application.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImplementation implements UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityService authorityService;

    @Override
    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public List<String> getAllUsernames()
    {
        return userRepository.findAllUsernames();
    }

    @Override
    public List<User> getManagers()
    {
        String managerAuthority = "ROLE_MANAGER";
        List<Authority> authorityList = authorityService.getByIdAuthority(managerAuthority);
        List<User> userList = new ArrayList<>();
        for (Authority authority : authorityList)
        {
            userList.add(authority.getAuthorityId().getUsername());
        }
        return userList;
    }

    @Override
    public List<User> getOnlyEmployees()
    {
        String managerAuthority = "ROLE_MANAGER";
        List<Authority> authorityList = authorityService.getByIdAuthority(managerAuthority);
        List<User> userList = getUsers();
        Set<User> employeeSet = new HashSet<>(userList);
        for (Authority authority : authorityList)
        {
            User user = authority.getAuthorityId().getUsername();
            String username = user.getUserName();
            for (User user1 : userList)
            {
                String username1 = user1.getUserName();
                if (username.equals(username1))
                {
                    employeeSet.remove(user1);
                }
            }
        }
        return new ArrayList<>(employeeSet);
    }

    @Override
    public User getUserInfo(String username) {
        return userRepository.getUserInfo(username);
    }

    @Override
    public User getUser(String username)
    {
        return userRepository.getReferenceById(username);
    }

    @Override
    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user)
    {
        userRepository.delete(user);
    }

    @Override
    public UserDetail getUserDetailId(String username) {
        return userRepository.getUserDetailId(username);
    }

    @Override
    public User getUserWithUserDetailId(int userDetailId) {
        return userRepository.getUserNameWithUserDetailId(userDetailId);
    }

}
