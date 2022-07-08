package com.service.impl;

import com.dao.UserLoginDao666;
import com.dao.UserLoginDao666;
import com.entity.User;
import com.exception.ServiceException;
import com.github.pagehelper.PageInfo;
import com.service.UserLoginService666;
import com.vo.UserBackVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserLoginServiceImpl666 implements UserLoginService666 {
    @Autowired
    UserLoginDao666 userLoginDao;
    @Override

    public User findUserBylogin_name(User u) throws ServiceException {
        User user = userLoginDao.findUserByLogin_name(u);
        if(user==null){
            throw new ServiceException("用户名错误");
        }
        return user;
    }

    @Override
    public PageInfo<User> findAllUser(int pageNo, UserBackVo u) throws ServiceException {
        return null;
    }

    @Override
    public User findUserById(int id) throws ServiceException {
        User user = userLoginDao.findUserByid(id);
        if(user==null){
            throw new ServiceException("用户名错误");
        }
        return user;
    }

    @Override
    public int updateUserInfo(User u) throws ServiceException {
        User user = userLoginDao.findUserByLogin_name(u);
        if(user==null){
            throw new ServiceException("没有该用户");
        }
        else{
            int i = userLoginDao.updateUserInfoByid(u);
            return i;
        }
    }

    @Override
    public int findUidByName(String name) {
        return 0;
    }
}
