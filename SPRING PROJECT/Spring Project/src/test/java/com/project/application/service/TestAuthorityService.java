package com.project.application.service;

import com.project.application.dao.AuthorityRepository;
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
class TestAuthorityService
{
    @Mock
    AuthorityRepository authorityRepository;

    @InjectMocks
    AuthorityService authorityService = new AuthorityServiceImplementation();

    @Test
    void testSaveAuthority()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        AuthorityId authorityId = new AuthorityId(user1, "ROLE_EMPLOYEE");
        Authority myAuthority = new Authority(authorityId);

        when(authorityRepository.save(myAuthority)).thenReturn(myAuthority);

        authorityService.saveAuthority(myAuthority);

        verify(authorityRepository).save(myAuthority);
    }


    @Test
    void testGetAllAuthorities()
    {
        List<Authority> authorityList = new ArrayList<>();

        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        AuthorityId authorityId1 = new AuthorityId(user1, "ROLE_EMPLOYEE");
        Authority authority1 = new Authority(authorityId1);

        AuthorityId authorityId2 = new AuthorityId(user1, "ROLE_MANAGER");
        Authority authority2 = new Authority(authorityId2);

        authorityList.add(authority1);
        authorityList.add(authority2);

        when(authorityRepository.findAll()).thenReturn(authorityList);

        List<Authority> fetchedList = authorityService.getAllAuthorities();

        assertEquals(fetchedList.get(0).getAuthorityId().getUsername(), authorityList.get(0).getAuthorityId().getUsername());
    }

    @Test
    void testGetByIdUsername()
    {
        List<Authority> authorityList = new ArrayList<>();

        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        AuthorityId authorityId1 = new AuthorityId(user1, "ROLE_EMPLOYEE");
        Authority authority1 = new Authority(authorityId1);

        AuthorityId authorityId2 = new AuthorityId(user1, "ROLE_MANAGER");
        Authority authority2 = new Authority(authorityId2);

        authorityList.add(authority1);
        authorityList.add(authority2);

        when(authorityRepository.findByAuthorityIdUsername(user1)).thenReturn(authorityList);

        List<Authority> fetchedList = authorityService.getByIdUsername(user1);

        assertEquals(fetchedList.get(0).getAuthorityId().getUsername(), authorityList.get(0).getAuthorityId().getUsername());
    }

    @Test
    void testGetByIdAuthority()
    {

        List<Authority> authorityList = new ArrayList<>();

        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        AuthorityId authorityId1 = new AuthorityId(user1, "ROLE_EMPLOYEE");
        Authority authority1 = new Authority(authorityId1);

        authorityList.add(authority1);

        when(authorityRepository.findByAuthorityIdAuthority("ROLE_EMPLOYEE")).thenReturn(authorityList);

        List<Authority> fetchedList = authorityService.getByIdAuthority("ROLE_EMPLOYEE");

        assertEquals(fetchedList.get(0).getAuthorityId().getUsername(), authorityList.get(0).getAuthorityId().getUsername());
    }

    @Test
    void testGetByIdUsernameAndAuthority()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        AuthorityId authorityId1 = new AuthorityId(user1, "ROLE_EMPLOYEE");
        Authority authority1 = new Authority(authorityId1);

        when(authorityRepository.findByAuthorityId(any())).thenReturn(authority1);

        Authority fetchedAuthority = authorityService.getByIdUsernameAndAuthority(user1, "ROLE_EMPLOYEE");

        assertEquals(authority1.getAuthorityId().getUsername(), user1);
    }

    @Test
    void testGetIfManagerAuthority()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        AuthorityId authorityId1 = new AuthorityId(user1, "ROLE_EMPLOYEE");
        Authority authority1 = new Authority(authorityId1);

        Authority fetchedAuthority = authorityService.getIfManagerAuthority(user1);

        assertEquals(authority1.getAuthorityId().getUsername(), user1);
    }

    @Test
    void testRemoveAuthority()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        AuthorityId authorityId1 = new AuthorityId(user1, "ROLE_EMPLOYEE");
        Authority authority1 = new Authority(authorityId1);

        doNothing().when(authorityRepository).delete(authority1);

        authorityService.removeAuthority(authority1);

        verify(authorityRepository).delete(authority1);
    }

    @Test
    void testMakeManagerAuthority()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        AuthorityId authorityId1 = new AuthorityId(user1, "ROLE_EMPLOYEE");
        Authority authority1 = new Authority(authorityId1);

        String managerAuthority = "ROLE_MANAGER";

        when(authorityRepository.save(any())).thenReturn(authority1);

        authorityService.makeManagerAuthority(user1);

        assertEquals(authority1.getAuthorityId().getUsername(), user1);
    }

    @Test
    void testRemoveManagerAuthority()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        AuthorityId authorityId1 = new AuthorityId(user1, "ROLE_EMPLOYEE");
        Authority authority1 = new Authority(authorityId1);

        authorityService.removeManagerAuthority(user1);

        assertEquals(authority1.getAuthorityId().getUsername(), user1);

    }

    @Test
    void testDeleteAllAuthoritiesOf()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        User user1 = new User("Suresh" ,"{bcrypt}$2a$12$hnn//gmN9uAtgBU/I3NwpOU4m4JNWt99dSUxlkdhsKNUGO4wcQlNW", 1, userDetail1);

        AuthorityId authorityId1 = new AuthorityId(user1, "ROLE_EMPLOYEE");
        Authority authority1 = new Authority(authorityId1);

        List<Authority> authorityList = new ArrayList<>();
        authorityList.add(authority1);

        doNothing().when(authorityRepository).deleteAll(any());

        authorityService.deleteAllAuthoritiesOf(user1);

        verify(authorityRepository).deleteAll(any());
    }

}
