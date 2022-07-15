package com.project.application.service;

import com.project.application.dao.UserDetailRepository;
import com.project.application.entity.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailServiceImplementation implements UserDetailService
{
    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public List<UserDetail> getDetails()
    {
        return userDetailRepository.findAll();
    }

    @Override
    public UserDetail getDetail(int id)
    {
        return userDetailRepository.getReferenceById(id);
    }

    @Override
    public void saveDetail(UserDetail userDetail)
    {
        userDetailRepository.save(userDetail);
    }

    @Override
    public void deleteDetail(UserDetail userDetail)
    {
        userDetailRepository.delete(userDetail);
    }
}
