package com.dao;

import com.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface UserDao {

    User selectByLoginnameAndPassword(User user);
    public void insertUser(User u);

    List<User> selectByALL(@Param("login_name") String login_name,@Param("nickname") String nickname,
                           @Param("role") String role,@Param("email") String email,
                           @Param("create_start_date") Date create_start_date,@Param("create_end_date") Date create_end_date,
                           @Param("login_start_date") Date login_start_date,@Param("login_end_date") Date login_end_date);

    void updateUser(User user);

    void updateUserStatus(User user);

    List<User> selectUserById(Integer id);



}
