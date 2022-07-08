package com.service.impl;

import com.dao.NameForCourseIdDao666;
import com.entity.Chapter;
import com.entity.Course;
import com.entity.Resource;
import com.service.NameForCourseIdService666;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NameForCourseIdServiceImpl666 implements NameForCourseIdService666 {

    @Autowired
    NameForCourseIdDao666 nameForCourseIdDao;
    @Override
    public Course findCourseIdByCourseName(String courseName) {
        return nameForCourseIdDao.selectCourseIdByCourseName(courseName);
    }

    @Override
    public List<Resource> findResourceByCourseId(Integer courseId) {
        return nameForCourseIdDao.selectResource(courseId);
    }

    @Override
    public List<Chapter> findChapterByCourseId(Integer courseId) {
        return nameForCourseIdDao.selectChapterByCourseId(courseId);
    }
}
