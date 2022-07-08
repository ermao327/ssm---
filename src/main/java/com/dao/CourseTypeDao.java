package com.dao;

import com.entity.CourseType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface CourseTypeDao {

    List<CourseType> selectByAll(@Param("parent_id") Integer parent_id);

    void updateStatus(CourseType courseType);

    List<CourseType> selectCourseTypeById(Integer id);

    List<CourseType> selectCourseTypeByParentId();

    void updateCourseTypeName(CourseType courseType);

    void updateCourseType(CourseType courseType);

    void insertCourseType(CourseType courseType);

    CourseType selectByName(String type_name);

    Set<CourseType> selectTypeChildren(Integer id);

}
