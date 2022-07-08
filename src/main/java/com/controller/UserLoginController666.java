package com.controller;

import com.constant.Constant;
import com.entity.GoldPoints;
import com.entity.User;
import com.exception.ServiceException;
import com.service.impl.GoldPointsServiceImpl666;
import com.service.impl.UserLoginServiceImpl666;
import com.service.impl.UserServiceImpl666;
import com.util.AjaxResult;
import com.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("/user666")
public class UserLoginController666 {

    @Autowired
    UserServiceImpl666 userService;
    @Autowired
    UserLoginServiceImpl666 userLoginService;
    @Autowired
    GoldPointsServiceImpl666 gpS;


     // 前台注册
    @RequestMapping("/regist.do")
    @ResponseBody
    public AjaxResult regist(User u) {
        try {
            userService.regist(u);
//            System.out.println(AjaxResult.isOk(u).isSuccess());
            return AjaxResult.isOk(u);
        } catch (ServiceException e) {
            e.printStackTrace();
            return AjaxResult.isFail(e.getMessage());
        }
    }

    //前台登录
    @PostMapping("/loginFront.do")
    @ResponseBody
    public AjaxResult login(HttpServletRequest request, String login_name, String password) {
        AjaxResult ajax = new AjaxResult();
        try {
            User u = userService.findUser(login_name, password);
//            u.setLogin_date(new Date());
//            userService.updateUser(u);
            request.getSession().setAttribute(Constant.SESSION_USER, u);
//            System.out.println("-------------");
            ajax.setSuccess(true);
        } catch (ServiceException e) {
            e.printStackTrace();
            ajax.setMsg(e.getMessage());
        }

        return ajax;
    }
     //前台修改
    @RequestMapping("/modifyUser")
    @ResponseBody
    public AjaxResult modifyUser(String id, String password, String email, String nickname, HttpSession session) {
        try {


            User user = (User) session.getAttribute(Constant.SESSION_USER);
            user.setPassword(password);
            user.setNickname(nickname);
            user.setEmail(email);
            userLoginService.updateUserInfo(user);
            return AjaxResult.isOk("修改成功", user);
        } catch (ServiceException e) {
            e.printStackTrace();
            return AjaxResult.isFail(e.getMessage());
        }
    }


      //前台退出（使缓存失效）
    @RequestMapping("/loginOut1.do")
    public String loginOut(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();

        return "/front/front_index";
    }

    //找到当前登录的用户信息
    @RequestMapping("/findUserLogin")
    @ResponseBody
    public AjaxResult findUserLogin(HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        return AjaxResult.isOk(user);
    }

    //判断当前是否有用户正在登录
    @RequestMapping("/islogin")
    @ResponseBody
    public AjaxResult isLogin(HttpSession session) {
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        if (user == null) {
            return AjaxResult.isFail("当前无用户");
        }
        return AjaxResult.isOk(user);
    }


     // 检验登录名
    @RequestMapping("/check.do")
    @ResponseBody
    public AjaxResult checkName(String login_name) {

        String str = "[a-zA-Z0-9_]{3,10}$";
        if (!login_name.matches(str)) {
            return AjaxResult.isFail("用户名只能由3-10位字母、数字和下划线组成");
        } else {
            return AjaxResult.isOk();
        }
    }

    /*
    注册时检验邮箱
     */
    @RequestMapping("/checkEmail")
    @ResponseBody
    public AjaxResult checkEmail(String email) {
        String str = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        if (!email.matches(str)) {
            return AjaxResult.isFail("请输入正确邮箱格式");
        }
        try {
            User user = userService.selectUserByEmail(email);
            return AjaxResult.isOk();

        } catch (ServiceException e) {
            return AjaxResult.isFail(e.getMessage());
        }
    }

    //登录时检验用户名
    @RequestMapping("/checkLogin_Name")
    @ResponseBody
    public AjaxResult checkLogin_Name(String login_name) {
        try {
            User user = userService.selectUser(login_name);
            return AjaxResult.isOk();
        } catch (ServiceException e) {
            e.printStackTrace();
            return AjaxResult.isFail(e.getMessage());
        }
    }

    //登录时检验密码
    @RequestMapping("/checkPassword")
    @ResponseBody
    public AjaxResult checkPassword(String login_name, String password) {
        try {
            User user = userService.findUser(login_name, password);
            return AjaxResult.isOk();
        } catch (ServiceException e) {
            e.printStackTrace();
            return AjaxResult.isFail(e.getMessage());
        }
    }

    //修改时校验旧密码
    @RequestMapping("/checkOldPassword")
    @ResponseBody
    public AjaxResult checkOldPassword(HttpSession session) {
        try {
            User user = (User) session.getAttribute(Constant.SESSION_USER);
            return AjaxResult.isOk(user);
        } catch (ServiceException e) {
            e.printStackTrace();
            return AjaxResult.isFail(e.getMessage());
        }
    }

    //修改时校验邮箱
    @RequestMapping("/checkModifyEmail")
    @ResponseBody
    public AjaxResult checkModifyEmail(String email) {
        String str = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        if (!email.matches(str)) {
            return AjaxResult.isFail("请输入正确邮箱格式");
        }
        try {
            User user = userService.selectUserByEmail(email);
            return AjaxResult.isOk("该邮箱可以使用");

        } catch (ServiceException e) {
            return AjaxResult.isFail(e.getMessage());
        }
    }

    //查询上次登录时间，判断是否需要签到
    @RequestMapping("/findLoginDate.do")
    @ResponseBody
    public Boolean findLoginDate(HttpSession session) {
        User u = (User) session.getAttribute(Constant.SESSION_USER);
//        System.out.println("============");
        Date lastLoginDate = gpS.getLastLoginDate(u);
        boolean isNeedSign = false;
        if (lastLoginDate == null) {
            isNeedSign = true;
        } else {
            isNeedSign = DateUtil.isNeedSign(lastLoginDate);
        }
//        System.out.println("是否需要签到" + isNeedSign);
        return isNeedSign;
    }

    //执行签到
    @RequestMapping("/updateLoginDate.do")
    @ResponseBody
    public AjaxResult updateLoginDate(HttpSession session) {
//        System.out.println("----------");
        User user = (User)session.getAttribute(Constant.SESSION_USER);
//        System.out.println("------");
        int user_id = user.getId();
//        System.out.println(user_id);
//        System.out.println("------");
        GoldPoints gp = new GoldPoints(user_id, 5, 0, "签到获得5积分", new Date());
        int i = gpS.insertGoldPoint(gp);
        if (i == 1) {
            return AjaxResult.isOk("签到成功");
        } else {
            return AjaxResult.isFail("网络异常，签到失败");
        }

    }
}