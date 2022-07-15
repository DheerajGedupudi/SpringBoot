package com.project.application.service;

import com.project.application.entity.UserDetail;

import java.util.List;

public interface UserDetailService
{

    public List<UserDetail> getDetails();

    public UserDetail getDetail(int id);

    public void saveDetail(UserDetail userDetail);

    public void deleteDetail(UserDetail userDetail);
}
