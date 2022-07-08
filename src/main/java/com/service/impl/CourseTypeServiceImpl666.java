package com.service.impl;

import com.dao.CourseTypeDao666;
import com.entity.Course;
import com.entity.CourseType;
import com.exception.ServiceException;
import com.github.pagehelper.PageInfo;
import com.service.CourseTypeService666;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CourseTypeServiceImpl666 implements CourseTypeService666 {
@Autowired
CourseTypeDao666 courseTypeDao;


    @Override
    public List<CourseType> findCourseTypes() {
        List<CourseType> courseTypes =
                courseTypeDao.selectCourseType();
        for (CourseType courseType : courseTypes) {
            if (courseType.getChildrens() != null && courseType.getChildrens().size() != 0){
                Set<CourseType> clists = courseType.getChildrens();
                List<Course> courses = new ArrayList<>();
                //二级列表查询
                for (CourseType clist : clists) {
                    if (clist.getCourses() != null && clist.getCourses().size() != 0){
                        List<Course> cs = clist.getCourses();
                        for (Course cours : cs) {
                            courses.add(cours);
                        }
                    }
                }
                Collections.sort(courses, Comparator.comparing(Course::getClick_number).reversed());
                int listLen = (courses.size() > 4 ? 4 : courses.size());
                ArrayList<Course> courses1 = new ArrayList<>();
                for (int i = 0; i < listLen; i++){
                    courses1.add(courses.get(i));
                }
                courseType.setCourses(courses1);
            }
        }
        return courseTypes;
    }

    @Override
    public List<CourseType> findByParentId(Integer pid) {
        List<CourseType> courseTypes = courseTypeDao.selectByParentId(pid);
        if(courseTypes==null){
            throw new ServiceException("当前没有课程");
        }
        return courseTypes;
    }

    @Override
    public PageInfo<CourseType> findCourseType() {
        return null;
    }
}
