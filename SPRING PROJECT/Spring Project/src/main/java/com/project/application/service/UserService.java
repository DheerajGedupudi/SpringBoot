package com.project.application.service;

import com.project.application.entity.User;
import com.project.application.entity.UserDetail;

import java.util.List;

public interface UserService
{

    public List<User> getUsers();

    public List<String> getAllUsernames();

    public List<User> getManagers();

    public List<User> getOnlyEmployees();

    public User getUserInfo(String username);

    public User getUser(String username);

    public void saveUser(User user);

    public void deleteUser(User user);

    public UserDetail getUserDetailId(String username);

    public User getUserWithUserDetailId(int userDetailId);

}
