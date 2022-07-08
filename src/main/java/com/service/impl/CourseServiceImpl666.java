package com.service.impl;

import com.dao.CourseDao666;
import com.dao.CourseTypeDao666;
import com.entity.Chapter;
import com.entity.Course;
import com.entity.CourseType;
import com.exception.DataAccessException;
import com.exception.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CourseService666;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseServiceImpl666 implements CourseService666 {
    @Autowired
    CourseDao666 courseDao;
    @Autowired
    CourseTypeDao666 ctDao;
    @Override
    public PageInfo findByCourseName(Integer pageNo, String course_name) {
        System.out.println("========");
        System.out.println(course_name);
        PageHelper.startPage(pageNo, 5);
        List<Course> courses = courseDao.selectByCourseName(course_name);
        return new PageInfo(courses);    }



    @Override
    public PageInfo findCoursesByCourseTypeId(Integer pageNo, Integer ctid) {
        PageHelper.startPage(pageNo, 5);
        List<Course> courses = courseDao.selectByCourseTypeId(ctid);
        return new PageInfo(courses);    }

    @Override
    public PageInfo findCoursesByCourseTypeId2(Integer pageNo, Integer ctid) {
        PageHelper.startPage(pageNo, 5);
        List<Course> courses = courseDao.selectByCourseTypeId2(ctid);
        return new PageInfo(courses);    }

    @Override
    public List<Course> findCourseByCourseId(Integer courseId) {
        return null;
    }

    @Override
    public PageInfo<Course> findCourse(Integer pageNo) {
        return null;
    }

    @Override
    public int modifyCourseById(Integer id, Integer status) {
        return 0;
    }

    @Override
    public int insertCourseByCondition(Course course) {
        return 0;
    }

    @Override
    public Course findCourseById(Integer id) {
        return null;
    }

    @Override
    public int modifyCourseByCondition(Course course) {
        return 0;
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
    public int updateCourseTypeStatusById(CourseType ct) {
        return 0;
    }

    @Override
    public int addCourseType(CourseType ct) {
        return 0;
    }

    @Override
    public List<CourseType> findCourseTypeList() {
        return null;
    }

    @Override
    public PageInfo<Course> selectCourses(int PageNo) throws ServiceException {
        return null;
    }

    @Override
    public List<Course> selectTop15() {
        List<Course> top15 = null;
        try {
            top15 = courseDao.findTop15();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("top15查询失败");
        }
        return top15;    }

    @Override
    public List<Course> findTop4ByGroup() {
        return null;
    }
}
