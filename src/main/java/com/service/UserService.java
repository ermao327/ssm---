package com.service;

import com.entity.User;
import com.exception.ServiceException;
import com.github.pagehelper.PageInfo;

import java.util.Date;

public interface UserService {
	public User login(User user) throws ServiceException;



	public void regist(User u)throws ServiceException;

	void modifyUser(User user) throws ServiceException;


	void modifyUserStatus(User user) throws ServiceException;

	PageInfo<User> findBackUserByAll(Integer pageNo,String login_name,String nickname,
									 String role,String email,
									 Date create_start_date,Date create_end_date,
									 Date login_start_date,Date login_end_date) throws ServiceException;

	PageInfo<User> findUserById(Integer id)throws ServiceException;
}
