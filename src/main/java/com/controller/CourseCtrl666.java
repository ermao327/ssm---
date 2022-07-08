package com.controller;


import com.entity.Course;
import com.entity.CourseType;
import com.exception.ServiceException;
import com.github.pagehelper.PageInfo;
import com.service.CourseService666;
import com.service.CourseTypeService666;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/course666")
public class CourseCtrl666 {

    @Autowired
    CourseService666 service;
    @Autowired
    CourseTypeService666 courseTypeService;

    @RequestMapping("/findAllCourse.do")
    @ResponseBody
    public PageInfo findCoursesById(Integer pageNo, String course_name,Integer course_type_id,HttpSession session){
        System.out.println("course_name:"+course_name);
        System.out.println("course_type_id:"+course_type_id);
        String course_name2 = (String) session.getAttribute("course_name");
        //点击搜索进入此函数
        if(!course_name.equals("")){
            System.out.println("333");
            return service.findByCourseName(pageNo, course_name);
        }
        //点击方向栏的全部
        if( course_type_id==null ||course_type_id == 0){
            System.out.println("555");
            return service.findByCourseName(pageNo, course_name);
        }
        //判断传入的course_type_id属于哪一级
        List<CourseType> list = courseTypeService.findByParentId(course_type_id);
        //三级id
        if(list.size() == 0){
            System.out.println("777");
            return service.findCoursesByCourseTypeId2(pageNo, course_type_id);
        }
        //顶级id
        System.out.println("999");
        return service.findCoursesByCourseTypeId(pageNo, course_type_id);
    }

    //展示最下方前15课程
    @RequestMapping("/showTop15.do")
    @ResponseBody
    public List<Course> getTop15 (){
        List<Course> list = null;
        try {
        list =  service.selectTop15();
    } catch (Exception e) {
        e.printStackTrace();
        throw new ServiceException("top15展示失败");
    }
        return list;
}


}
