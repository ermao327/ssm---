package com.service.impl;

import com.constant.Constant;
import com.dao.CourseDao;
import com.entity.Chapter;
import com.entity.Course;
import com.entity.CourseType;
import com.exception.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDao dao;

    @Override
    public PageInfo<Course> findCourse(Integer pageNo,Course course) {
        PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
        List<Course> list = dao.selectCourses(course);
        if (null == list){
            throw new ServiceException("没有更多课程了...");
        }
        return new PageInfo<Course>(list);
    }

    @Override
    public int insertCourseByCondition(Course course) {
        dao.insertCourseByCondition(course);
        return 0;
    }

    @Override
    public PageInfo<Course> selectCourses(int PageNo) {
        return null;
    }

    @Override
    public Course findCourseById(Integer id) {
        return dao.selectCourseById(id);
    }

    @Override
    public int modifyCourseById(Integer id, Integer status) {
        dao.updateCourseById(id,status);
        return 0;
    }

    @Override
    public int modifyCourseByCondition(Course course) {
        dao.updateCourseByCondition(course);
        return 0;
    }

    @Override
    public List<Course> findCourseByCourseId(Integer courseId) {
        return null;
    }

    @Override
    public PageInfo findByCourseName(Integer pageNo, String courseName) {
        return null;
    }



    @Override
    public List<Course> findTop3CoursesByCourseTypeId(Integer ctid) {
        return dao.selectTop3ByCourseTypeId(ctid);
    }



    @Override
    public PageInfo findCoursesByCourseTypeId2(Integer pageNo, Integer ctid) {
        return null;
    }

    @Override
    public List<Course> getCourseList() {
        return null;
    }

    @Override
    public List<Chapter> getChapterList(int cid) {
        return null;
    }

    @Override
    public PageInfo<CourseType> getCourseTypeList(int pageNo, int parent_id) {
        return null;
    }

    @Override
    public CourseType findCourseTypeByid(int id) {
        return null;
    }

    @Override
    public List<CourseType> findCourseTypeList() {
        return null;
    }

    @Override
    public List<Course> selectTop15() {
        return null;
    }

    @Override
    public List<Course> findTop4ByGroup() {
        return null;
    }

    @Override
    public int updateCourseTypeStatusById(CourseType ct) {
        return 0;
    }

    @Override
    public int addCourseType(CourseType ct) {
        return 0;
    }

    @Override
    public List<CourseType> findCourseTypes() {
        List<CourseType> list = dao.selectCourseTypes();
        System.out.println(list);
        if (null == list){
            throw new ServiceException("没有更多了...");
        }
        return list;
    }
}
