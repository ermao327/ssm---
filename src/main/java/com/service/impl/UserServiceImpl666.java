package com.service.impl;

import com.dao.UserDao666;
import com.entity.User;
import com.exception.ServiceException;
import com.service.UserService666;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl666 implements UserService666 {

    @Autowired
    UserDao666 userDao;
    @Override
    public User findUserById(Integer id) {
        return null;
    }

    @Override
    public User selectUserByEmail(String email) {
        User user = userDao.selectUserByEmail(email);
        if(user!=null){
            throw new ServiceException("邮箱已被注册");
        }
        return null;
    }

    @Override
    public User findUser(String login_name, String password) throws ServiceException {
        User user = userDao.selectByLoginNameAndPassword(login_name, password);
        if(user ==null){
            throw new ServiceException("用户/密码名错误");
        }
        return user;
    }

    @Override
    public User selectUser(String login_name) throws ServiceException {
        User user = userDao.selectByLogin_Name(login_name);
        if(user ==null){
            throw new ServiceException("用户不存在");
        }
        return user;
    }

    @Override
    public void regist(User u) throws ServiceException {

        User user = userDao.selectByLoginname(u);
        if(user ==null){

            u.setRole("0");
            u.setLogin_date(null);
            u.setCreate_date(new Date());
            u.setStatus(0);
            userDao.insertUser(u);
        }
        else{
            throw new ServiceException("用户名已被注册，请更换");
        }

    }

    @Override
    public List<String> getNames() throws ServiceException {
        return null;
    }

    @Override
    public List<String> getEmails() throws ServiceException {
        return null;
    }

    @Override
    public List<User> getAllUser() throws ServiceException {
        return null;
    }

    @Override
    public int updateUser(User u) throws ServiceException {
        return userDao.updateUser(u);
    }

    @Override
    public Date getLoginDateByUser(User u) throws ServiceException {
        return null;
    }
}
