package com.service.impl;

import com.constant.Constant;
import com.dao.UserDao;
import com.entity.User;
import com.exception.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDao dao;
	@Override
	public User login(User user) throws ServiceException {
		User u = dao.selectByLoginnameAndPassword(user);
		if(u == null){
			throw new ServiceException("用户名/密码错误");
		}
		if(!u.getRole().equals("admin")){
			throw new ServiceException("您不是管理员，无法登录");
		}
		return u;
	}




	@Override
	public void regist(User u) throws ServiceException {
		User user = dao.selectByLoginnameAndPassword(u);
		if(user == null){
			dao.insertUser(u);
		}else{
			throw new ServiceException("用户名已成功注册");
		}
	}

	@Override
	public void modifyUser(User user) throws ServiceException {
		//修改用户信息
		dao.updateUser(user);
	}

	@Override
	public void modifyUserStatus(User user) throws ServiceException {
		//修改用户状态
		dao.updateUserStatus(user);
	}

	@Override
	public PageInfo<User> findBackUserByAll(Integer pageNo,String login_name,String nickname,
											String role,String email,Date create_start_date,Date create_end_date,
											Date login_start_date,Date login_end_date) throws ServiceException {
		//开启分页插件的分页功能
		PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
		List<User> users = dao.selectByALL(login_name,nickname,role,email,
				create_start_date,create_end_date,login_start_date,login_end_date);
		System.out.println(role+"--------------");
		if (null == users) {
			throw new ServiceException("没有更多用户了...");
		}
		return new PageInfo<User>(users);
	}

	@Override
	public PageInfo<User> findUserById(Integer id) throws ServiceException {

		List<User> users = dao.selectUserById(id);

		PageInfo<User> info = new PageInfo<User>(users);
		return info;
	}

}
