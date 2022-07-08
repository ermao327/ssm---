package com.controller;

import com.entity.GoldPoints;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.service.FrontUserService;
import com.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/gp")
public class FrontUserController {
    @Autowired
    FrontUserService frontUserService;

    //积分兑换金币
    @RequestMapping("/exchange")
    @ResponseBody
    public void exchange(HttpServletRequest request, HttpServletResponse response) {

        Integer points=Integer.valueOf(request.getParameter("points"));
        Integer gold_count = points / 10;
        Integer point_count = -(points);
        String info1= "积分兑换金币";
        Date create_date = new Date();
        frontUserService.insertPointToGold(point_count, gold_count, info1, create_date);

    }



    //分页查询用户金币积分信息;分页
    @RequestMapping("/findAllRecords")
    @ResponseBody
    public AjaxResult findAllRecords(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        AjaxResult result = new AjaxResult();

        Integer pageNo = 1;
        if(!(request.getParameter("pageNo")==null||request.getParameter("pageNo").equals(""))){
            pageNo = Integer.valueOf(request.getParameter("pageNo"));
        }else{
            pageNo = 1;
        }

        PageInfo<GoldPoints> info = frontUserService.findAllRecords(pageNo);
        result.setObj(info);

        return result;
    }

    //分页查询用户金币积分信息;不分页
    @RequestMapping("/findAllRecordsInfo")
    @ResponseBody
    public AjaxResult findAllRecordsInfo(HttpServletRequest request, HttpServletResponse response) throws ParseException {
        AjaxResult result = new AjaxResult();

        PageInfo<GoldPoints> info = frontUserService.findAllRecordsInfo();
        result.setObj(info);

        return result;
    }




}
