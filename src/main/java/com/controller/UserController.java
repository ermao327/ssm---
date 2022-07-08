package com.controller;

import com.constant.Constant;
import com.entity.User;
import com.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import com.service.UserService;
import com.util.AjaxResult;
import com.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userService;
	//后台修改用户状态禁用-启用

	@RequestMapping("/modifyBackUserStatus")
	@ResponseBody
	public void modifyUserStatus(HttpServletRequest request,User user)
	{
		System.out.println("modifyUserStatus");

		try{
			user.setId(user.getId());
			userService.modifyUserStatus(user);
		}catch(Exception e){
			System.out.println("修改状态失败");
		}
	}

	//修改信息
	@RequestMapping("/modifyBackUser")
	@ResponseBody
	public void modifyUser(HttpServletRequest request,User user)
	{
		System.out.println("modifyBackUser");

		try{
				user.setId(user.getId());
				userService.modifyUser(user);
		}catch(Exception e){
			System.out.println("修改用户失败");
		}
	}

	//显示用户信息(修改按钮)
	@RequestMapping("/showBackUser")
	@ResponseBody
	public AjaxResult findUser(HttpServletRequest request, HttpServletResponse response)
	{
		AjaxResult result = new AjaxResult(true, "查询成功", null);
		final Integer id = Integer.valueOf(request.getParameter("id"));
		try {
			PageInfo<User> info = userService.findUserById(id);
			result.setObj(info);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMsg(e.getMessage());
		}
		return result;
	}

	//查询用户信息
	@RequestMapping("/findBackUser")
	@ResponseBody
	public AjaxResult findBackUser(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		AjaxResult result = new AjaxResult();

		Integer pageNo = 1;
		if(!(request.getParameter("pageNo")==null||request.getParameter("pageNo").equals(""))){
			 pageNo = Integer.valueOf(request.getParameter("pageNo"));
		}else{
			 pageNo = 1;
		}

		 String login_name = String.valueOf(request.getParameter("login_name"));
		 String nickname = String.valueOf(request.getParameter("nickname"));
		 String role = String.valueOf(request.getParameter("role"));
		 String email = String.valueOf(request.getParameter("email"));

		String create_start_date1 = String.valueOf(request.getParameter("create_start_date"));
		String create_end_date1 = String.valueOf(request.getParameter("create_end_date"));
		String login_start_date1 = String.valueOf(request.getParameter("login_start_date"));
		String login_end_date1 = String.valueOf(request.getParameter("login_end_date"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date create_start_date = sdf.parse("1900-01-01");
		java.util.Date create_end_date = sdf.parse("2999-12-31");
		java.util.Date login_start_date = sdf.parse("1900-01-01");
		java.util.Date login_end_date = sdf.parse("2999-12-31");


		if(create_start_date1!=null&&!create_start_date1.equals("")){
			create_start_date = sdf.parse(create_start_date1);
		}
		if(create_end_date1!=null&&!create_end_date1.equals("")){
			create_end_date = sdf.parse(create_end_date1);
		}
		if(login_start_date1!=null&&!login_start_date1.equals("")){
			login_start_date = sdf.parse(login_start_date1);
		}
		if(login_end_date1!=null&&!login_end_date1.equals("")){
			login_end_date = sdf.parse(login_end_date1);
		}

			PageInfo<User> info = userService.findBackUserByAll(pageNo,login_name,nickname,role,email,
					create_start_date,create_end_date,login_start_date,login_end_date);
			result.setObj(info);

		return result;
	}




	@RequestMapping("/loginBack")
	@ResponseBody
	public AjaxResult login(HttpServletRequest request, User u, HttpServletResponse response) {
		AjaxResult result = new AjaxResult();
		String code =  request.getParameter("code");
		String localCode = (String) request.getSession().getAttribute("code");
		if(localCode ==null || !localCode.equals(code)){
			result.setMsg("验证码错误");
			return result;
		}

		System.out.println("controller:"+u);
		try {
			User user = userService.login(u);
			request.getSession().setAttribute("user", user);
			result.setSuccess(true);
			result.setObj(user);
		}catch (ServiceException e) {
			result.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	//解决获取不到session的问题，实现登录跳转显示欢迎xxx
	//	http://t.zoukankan.com/jasonboren-p-12285992.html
	@RequestMapping(value = "/getusername")
	public void getUsername(HttpServletRequest request, HttpServletResponse response)throws Exception{
		//先从session中获取到之前存在session中的用户信息，然后通过ObjectMapper输出返回一个json数据给html页面，由页面去解析这个json数据
		User user=(User)request.getSession().getAttribute("user");
		if(user!=null){
			ObjectMapper objectMapper=new ObjectMapper();
			objectMapper.writeValue(response.getOutputStream(),user);
		}
	}
	@RequestMapping("/getCode")
	public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("UserController.getCode()");
		CommonUtil.MyImage m = CommonUtil.getImage(null, 4, true, true);
		System.out.println("code=" + m.getCode());
		request.getSession().setAttribute("code", m.getCode());
		ServletOutputStream responseOutputStream = response.getOutputStream();
		// 输出图象到页面
		ImageIO.write(m.getImage(), "JPEG", responseOutputStream);
		// 以下关闭输入流！
		responseOutputStream.flush();
		responseOutputStream.close();
	}
	
	//退出登录
	@RequestMapping("/backloginOut")
	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		return "redirect:/backlogin";
	}	
	
	
	@RequestMapping("/regist")		
	public String regist(HttpServletRequest request,User u, HttpServletResponse response) {
		String code =  request.getParameter("code");
		String localCode = (String) request.getSession().getAttribute("code");
		if(localCode ==null || !localCode.equals(code)){
			System.out.println("验证码不正确");
			return "/backlogin.do";
		}
		System.out.println("userController:"+u);
		try {
			userService.regist(u);
			request.getSession().setAttribute(Constant.SESSION_USER, u);
		}catch (ServiceException e) {
			e.printStackTrace();
		}
		return "/backlogin";
	}
}
