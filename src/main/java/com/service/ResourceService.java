package com.service;

import com.entity.Chapter;
import com.entity.Course;
import com.entity.CourseType;
import com.entity.Resource;
import com.exception.ServiceException;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface ResourceService {
    PageInfo<Resource> findResourceByAll(Integer pageNo, String title, String login_name,
                                         Integer status, String file_type,
                                         Date  create_start_date,Date create_end_date ) throws ServiceException;


    void modifyStatus(Resource resource) throws ServiceException;

    PageInfo<Resource> queryResource();

    List<Course> selectCourseName();

    List<Chapter> selectChapter(String course_name);

    void modifyFrontResource(String id, String title, Integer cost_type,
                             Integer cost_number, Integer chapter_id, String original_name,
                             String file_type, String path, String course_name,Integer file_size);

    String selectpath(Integer id);

    String selectOriginal_name(Integer resourceId);

    void removeFrontResource(Integer id);

    void addFrontResource( String title, Integer cost_type, Integer cost_number,
                           Integer chapter_id, String original_name,
                           String file_type, String path,  Integer file_size);

    String selectname(Integer resourceId);

}



