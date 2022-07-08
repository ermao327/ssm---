package com.service;

import com.entity.User;
import com.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface UserService666 {

        public User findUserById(Integer id);

        public User selectUserByEmail(String email);

        public User findUser(String login_name, String password) throws ServiceException;

        public User selectUser(String login_name) throws ServiceException;

        public void regist(User u)throws ServiceException;

        public List<String> getNames() throws ServiceException;

        public List<String> getEmails() throws ServiceException;

        public List<User>  getAllUser() throws ServiceException;

        public  int updateUser(User u)throws ServiceException;

        public Date getLoginDateByUser(User u)throws ServiceException;


}
