package com.project.application.service;

import com.project.application.dao.UserRepository;
import com.project.application.entity.Authority;
import com.project.application.entity.User;
import com.project.application.entity.UserDetail;
import com.project.application.entity.id.AuthorityId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TestUserService
{
    @Mock
    UserRepository userRepository;

    @Mock
    AuthorityService authorityService;

    @InjectMocks
    UserService userService = new UserServiceImplementation();


    @Test
    void testGetUsers()
    {
        List<User> userList = new ArrayList<>();

        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);
        UserDetail userDetail2 = new UserDetail(2, "Mahesh", "Rao", "mahesh@gmail.com");
        User user2 = new User("Mahesh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail2);

        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> fetchedUsers = userService.getUsers();

        assertEquals(2, fetchedUsers.size());
        assertEquals(fetchedUsers.get(0).getUserName(), userList.get(0).getUserName());
    }

    @Test
    void testGetAllUsernames()
    {
        List<String> usernameList = new ArrayList<>();
        usernameList.add("Suresh");
        usernameList.add("Mahesh");

        when(userRepository.findAllUsernames()).thenReturn(usernameList);

        List<String> fetchedUsernames = userService.getAllUsernames();

        assertEquals(fetchedUsernames.get(0), usernameList.get(0));
    }

    @Test
    void testGetManagers()
    {
        UserDetail userDetail1 = new UserDetail(1, "Mahesh", "Rao", "mahesh@gmail.com");
        User user1 = new User("Mahesh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        List<Authority> managerAuthorities = new ArrayList<>();

        AuthorityId authorityId = new AuthorityId(user1, "ROLE_MANAGER");
        Authority managerAuthority = new Authority(authorityId);

        managerAuthorities.add(managerAuthority);

        when(authorityService.getByIdAuthority("ROLE_MANAGER")).thenReturn(managerAuthorities);

        List<Authority> fetchedAuthorities = authorityService.getByIdAuthority("ROLE_MANAGER");

        List<User> myList = userService.getManagers();

        List<User> managerList = new ArrayList<>();
        for (Authority authority : fetchedAuthorities)
        {
            managerList.add(authority.getAuthorityId().getUsername());
        }

        assertEquals(1, managerList.size());

    }

    @Test
    void testGetOnlyEmployees()
    {
        UserDetail userDetail1 = new UserDetail(1, "Mahesh", "Rao", "mahesh@gmail.com");
        User user1 = new User("Mahesh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        List<User> userList = new ArrayList<>();
        userList.add(user1);

        List<Authority> managerAuthorities = new ArrayList<>();

        AuthorityId authorityId = new AuthorityId(user1, "ROLE_MANAGER");
        Authority managerAuthority = new Authority(authorityId);

        managerAuthorities.add(managerAuthority);

        when(userRepository.findAll()).thenReturn(userList);
        when(authorityService.getByIdAuthority("ROLE_MANAGER")).thenReturn(managerAuthorities);


        List<Authority> fetchedAuthorities = authorityService.getByIdAuthority("ROLE_MANAGER");

        List<User> myList = userService.getOnlyEmployees();

        List<User> employeeList = new ArrayList<>();
        for (Authority authority : fetchedAuthorities)
        {
            employeeList.add(authority.getAuthorityId().getUsername());
        }

        assertEquals(1, employeeList.size());
    }

    @Test
    void testGetUserInfo()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        when(userRepository.getUserInfo("Suresh")).thenReturn(user1);

        User fetchedUser = userService.getUserInfo("Suresh");

        assertEquals(user1.getUserName(), fetchedUser.getUserName());
    }

    @Test
    void testGetUser()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        when(userRepository.getReferenceById("Suresh")).thenReturn(user1);

        User fetchedUser = userService.getUser("Suresh");

        assertEquals(user1.getUserName(), fetchedUser.getUserName());
    }

    @Test
    void testSaveUser()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        when(userRepository.save(user1)).thenReturn(user1);

        userService.saveUser(user1);

        verify(userRepository).save(user1);
    }

    @Test
    void testDeleteUser()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        doNothing().when(userRepository).delete(user1);

        userService.deleteUser(user1);

        verify(userRepository).delete(user1);

    }

    @Test
    void testGetUserDetailId()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        when(userRepository.getUserDetailId("Suresh")).thenReturn(userDetail1);

        UserDetail fetchedUserDetail = userService.getUserDetailId("Suresh");

        assertEquals(fetchedUserDetail.getFirstName(), userDetail1.getFirstName());
    }

    @Test
    void testGetUserWithUserDetailId()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        when(userRepository.getUserNameWithUserDetailId(1)).thenReturn(user1);

        User fetchedUser = userService.getUserWithUserDetailId(1);

        assertEquals(fetchedUser.getUserName(), user1.getUserName());
    }

}
