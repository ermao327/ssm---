package com.controller;

import com.entity.Course;
import com.entity.CourseType;
import com.github.pagehelper.PageInfo;
import com.service.CourseService;
import com.util.AjaxResult;
import com.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    //加载所有课程
    @RequestMapping("/findCourse")
    @ResponseBody
    public AjaxResult findCourse(HttpServletRequest request){
        AjaxResult result = new AjaxResult(true,"查询成功",null);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));

        String course_name = request.getParameter("course_name");
        String author = request.getParameter("author");
        Integer status = Integer.valueOf(request.getParameter("status"));
        Integer course_type_id =Integer.valueOf(request.getParameter("course_type_id"));
        String start = request.getParameter("begin_date");
        Date st = DateUtil.startDate(start);
        String end = request.getParameter("end_date");
        Date ed = DateUtil.endDate(end);

        Course course = new Course();
        course.setCourse_name(course_name);
        course.setAuthor(author);
        course.setStatus(status);
        course.setCourse_type_id(course_type_id);
        course.setSt(st);
        course.setEd(ed);

        try{
            PageInfo<Course> info = courseService.findCourse(pageNo,course);
            result.setObj(info);
        }catch(Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    //添加课程
    @RequestMapping("/addCourse")
    @ResponseBody
    public int addCourse(HttpServletRequest request){
        String course_name = request.getParameter("course_name");
        String author = request.getParameter("author");
        String cover_image_url =request.getParameter("multiFiles[0]");
        Integer recommendation_grade= Integer.valueOf(request.getParameter("recommendation_grade"));
        Integer course_type_id =Integer.valueOf(request.getParameter("course_type_id"));

        Course course = new Course();
        course.setCourse_name(course_name);
        course.setAuthor(author);
        course.setCover_image_url(cover_image_url);
        course.setRecommendation_grade(recommendation_grade);
        course.setCourse_type_id(course_type_id);
        course.setStatus(1);

        courseService.insertCourseByCondition(course);

        return 0;
    }

    //修改课程前的信息展示
    @RequestMapping("/show")
    @ResponseBody
    public AjaxResult show(HttpServletRequest request) {
        AjaxResult result = new AjaxResult();
        Integer id = Integer.valueOf(request.getParameter("id"));
        Course courseById = courseService.findCourseById(id);
        result.setSuccess(true);
        result.setObj(courseById);
        return result;
    }

    //改变课程状态（启用禁用）
    @RequestMapping("/toggle")
    @ResponseBody
    public void toggle(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer status = Integer.valueOf(request.getParameter("status"));
        courseService.modifyCourseById(id,status);
    }

    //修改课程
    @RequestMapping("/modifyCourse")
    @ResponseBody
    public int modifyCourse(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        String course_name = request.getParameter("course_name");
        String author = request.getParameter("author");
        String cover_image_url =request.getParameter("multiFiles[0]");
        Integer recommendation_grade= Integer.valueOf(request.getParameter("recommendation_grade"));
        Integer course_type_id =Integer.valueOf(request.getParameter("course_type_id"));

        Course course = new Course();
        course.setId(id);
        course.setCourse_name(course_name);
        course.setAuthor(author);
        course.setCover_image_url(cover_image_url);
        course.setRecommendation_grade(recommendation_grade);
        course.setCourse_type_id(course_type_id);
        courseService.modifyCourseByCondition(course);
        return 0;
    }



    //加载当前课程类型下的top3
    @RequestMapping("/loadRecourse")
    @ResponseBody
    public AjaxResult findTop3Courses(HttpServletRequest request){
        AjaxResult result = new AjaxResult(true,"查询成功",null);

        Integer id = Integer.valueOf(request.getParameter("course_id"));
        Course course = courseService.findCourseById(id);
        List<Course> list = courseService.findTop3CoursesByCourseTypeId(course.getCourse_type_id());

        try{
            result.setObj(list);
        }catch(Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }









    //查找课程类型
    @RequestMapping("/findBackCourseType2")
    @ResponseBody
    public AjaxResult findCourseType(){
        AjaxResult result = new AjaxResult(true,"查询成功",null);
        try{
            List<CourseType> list = courseService.findCourseTypes();
            result.setObj(list);
            System.out.println(result);
        }catch(Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

}
