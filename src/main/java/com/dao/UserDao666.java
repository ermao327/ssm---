package com.dao;

import com.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserDao666 {
    User selectByLoginname(User user);

    public User selectById(Integer id);

    public User selectUserByEmail(String email);

   public User selectByLoginNameAndPassword(@Param("login_name") String login_name, @Param("password") String password);

   public User selectByLogin_Name(String login_name);

    public void insertUser(User u);

    public List<User> selectAllUsers();

    public int updateUser(User u);

}
