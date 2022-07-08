package com.controller;

import com.entity.CourseType;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.service.CourseTypeService;
import com.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/ct")
public class CourseTypeController {
    @Autowired
    CourseTypeService courseTypeService;

    //修改课程类别
    @RequestMapping("/modifyCourseTypeName")
    @ResponseBody
    public void modifyUser(HttpServletRequest request, CourseType courseType) {
        System.out.println("modifyCourseTypeName");

        try {
            courseTypeService.modifyCourseTypeName(courseType);
        } catch (Exception e) {
            System.out.println("修改用户失败");
        }
    }

    //添加课程类别
    @RequestMapping("/addBackType")
    @ResponseBody
    public void addCourseType(HttpServletRequest request,CourseType courseType) {
        System.out.println("addBackType");
        String type_name = String.valueOf(request.getParameter("type_name"));
        Integer parent_id = Integer.valueOf(request.getParameter("parent_id"));
        Integer status = Integer.valueOf(request.getParameter("status"));

            courseTypeService.addCourseType(courseType);

    }

    //显示课程类别信息(修改按钮)
    @RequestMapping("/showBackName")
    @ResponseBody
    public AjaxResult findCourseType(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult result = new AjaxResult(true, "查询成功", null);
        final Integer id = Integer.valueOf(request.getParameter("id"));
        try {
            List<CourseType> info = courseTypeService.findCourseTypeById(id);
            result.setObj(info);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    //获取父类id
    @RequestMapping("/getList")
    @ResponseBody
    public AjaxResult getlist(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult result = new AjaxResult(true, "查询成功", null);
        try {
            List<CourseType> list = courseTypeService.selectCourseTypeByParentId();
            result.setObj(list);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    //后台修改用户状态禁用-启用
    @RequestMapping("/toggleStatus")
    @ResponseBody
    public void modifyCourseTypeStatus(HttpServletRequest request, CourseType courseType) {
        System.out.println("modifyStatus");

        try {
            courseType.setId(courseType.getId());
            courseTypeService.modifyStatus(courseType);
        } catch (Exception e) {
            System.out.println("修改状态失败");
        }
    }

    //查询子类别
    @RequestMapping("/getChildList")
    @ResponseBody
    public AjaxResult getChildList(HttpServletRequest request, HttpServletResponse response,Integer id) {
        AjaxResult result = new AjaxResult(true, "查询成功", null);
        try {
            Set<CourseType> list = courseTypeService.selectTypeChildren(id);
            result.setObj(list);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }


    //查询课程类别信息
    @RequestMapping("/findBackCourseType")
    @ResponseBody
    public AjaxResult findBackCourseType(HttpServletRequest request) {
        AjaxResult result = new AjaxResult();
        Integer pageNo = 1;
        if (!(request.getParameter("pageNo") == null || request.getParameter("pageNo").equals(""))) {
            pageNo = Integer.valueOf(request.getParameter("pageNo"));
        } else {
            pageNo = 1;
        }
         Integer parent_id = Integer.valueOf(request.getParameter("parent_id"));
        try {
            PageInfo<CourseType> info = courseTypeService.findBackCourseTypeByAll(pageNo,parent_id);
            result.setObj(info);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
