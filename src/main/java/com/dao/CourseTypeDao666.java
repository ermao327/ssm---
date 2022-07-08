package com.dao;

import com.entity.Course;
import com.entity.CourseType;

import java.util.List;


public interface CourseTypeDao666 {
    public List<CourseType> selectAll();
    List<CourseType> selectByParentId(Integer pid);
    Integer selectParentIdById(Integer id);
    public List<Course> selectCourseByCourseId();
    public List<CourseType> findByParentId(int parent_id);
    public CourseType selectCourseTypeById(int id);
    public List<CourseType> selectCourseType();
}
