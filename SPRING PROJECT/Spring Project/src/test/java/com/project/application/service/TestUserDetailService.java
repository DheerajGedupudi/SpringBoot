package com.project.application.service;

import com.project.application.dao.UserDetailRepository;
import com.project.application.entity.UserDetail;
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
class TestUserDetailService
{
    @Mock
    UserDetailRepository userDetailRepository;

    @InjectMocks
    UserDetailService userDetailService = new UserDetailServiceImplementation();

    @Test
    void testGetDetails()
    {
        List<UserDetail> userDetailList = new ArrayList<>();

        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");
        UserDetail userDetail2 = new UserDetail(2, "Mahesh", "Rao", "mahesh@gmail.com");

        userDetailList.add(userDetail1);
        userDetailList.add(userDetail2);

        when(userDetailRepository.findAll()).thenReturn(userDetailList);

        List<UserDetail> fetchedList = userDetailService.getDetails();

        assertEquals(fetchedList.get(0).getFirstName(), userDetailList.get(0).getFirstName());
    }

    @Test
    void testGetDetail()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");

        when(userDetailRepository.getReferenceById(1)).thenReturn(userDetail1);

        UserDetail fetchedUserDetail = userDetailService.getDetail(1);

        assertEquals(fetchedUserDetail.getFirstName(), userDetail1.getFirstName());
    }

    @Test
    void testSaveDetail()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");

        when(userDetailRepository.save(userDetail1)).thenReturn(userDetail1);

        userDetailService.saveDetail(userDetail1);

        verify(userDetailRepository).save(userDetail1);
    }

    @Test
    void testDeleteDetail()
    {
        UserDetail userDetail1 = new UserDetail(1, "Suresh", "Kumar", "suresh@gmail.com");

        doNothing().when(userDetailRepository).delete(userDetail1);

        userDetailService.deleteDetail(userDetail1);

        verify(userDetailRepository).delete(userDetail1);
    }
}
