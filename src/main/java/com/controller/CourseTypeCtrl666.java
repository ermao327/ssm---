package com.controller;

import com.entity.CourseType;
import com.exception.ServiceException;
import com.service.CourseService666;
import com.service.CourseTypeService666;
import com.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/ct666")
public class CourseTypeCtrl666 {

    @Autowired
    CourseTypeService666 ct;
@Autowired
CourseService666 courseService;

      //查询一级
    @RequestMapping("/findCourseType.do")
    @ResponseBody
    public AjaxResult selectCourseTypeOne() {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            List<CourseType> courseTypes = ct.findCourseTypes();
            ajaxResult.setObj(courseTypes);

        } catch (ServiceException e) {
            e.printStackTrace();
            ajaxResult.setMsg(e.getMessage());

        }
        return ajaxResult;
    }

    //根据id查询ParentId
    @RequestMapping("/findByParentId.do")
    @ResponseBody
    public AjaxResult selectCourseTypeTwo(String parent_id) {
//    System.out.println("pid:"+parent_id);
        int pid = Integer.parseInt(parent_id);
        AjaxResult ajaxResult = new AjaxResult();
        try {
            List<CourseType> courseTypes = ct.findByParentId(pid);
            ajaxResult.setObj(courseTypes);
        } catch (ServiceException e) {
            e.printStackTrace();
            ajaxResult.setMsg(e.getMessage());

        }
        return ajaxResult;
    }

}
