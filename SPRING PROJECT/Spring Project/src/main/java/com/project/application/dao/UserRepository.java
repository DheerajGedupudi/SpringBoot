package com.project.application.dao;

import com.project.application.entity.User;
import com.project.application.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String>
{
    @Query("Select userName from User")
    List<String> findAllUsernames();

    @Query("from User where userName=:username")
    User getUserInfo(String username);


    @Query("Select userDetail from User where userName=:username")
    UserDetail getUserDetailId(String username);

    @Query("from User where userDetailId=:myUserDetailId")
    User getUserNameWithUserDetailId(int myUserDetailId);
}
