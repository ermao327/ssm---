package com.service;

import com.entity.CourseType;
import com.entity.User;
import com.exception.ServiceException;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Set;

public interface CourseTypeService {

    PageInfo<CourseType> findBackCourseTypeByAll(Integer pageNo,Integer parent_id) throws ServiceException;

    void modifyStatus(CourseType courseType) throws ServiceException;

    void modifyCourseTypeName(CourseType courseType) throws ServiceException;


    void addCourseType(CourseType courseType) throws ServiceException;

    List<CourseType> findCourseTypeById(Integer id)throws ServiceException;

    List<CourseType> selectCourseTypeByParentId()throws ServiceException;

    Set<CourseType> selectTypeChildren(Integer id);

}
