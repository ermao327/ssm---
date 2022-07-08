package com.service.impl;

import com.constant.Constant;
import com.dao.ResourceDao;
import com.dao.UserDao;
import com.entity.*;
import com.exception.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    ResourceDao dao;

    @Override
    public PageInfo<Resource> findResourceByAll(Integer pageNo, String title, String login_name,
                                                Integer status, String file_type,
                                                Date create_start_date, Date create_end_date) throws ServiceException {

        //开启分页插件的分页功能
        PageHelper.startPage(pageNo, Constant.PAGE_SIZE);

        List<Resource> resources = dao.selectByAll(title,login_name,status,file_type,create_start_date,create_end_date);

        List<User> users = dao.selectUserAll();


        for (int i = 0; i < resources.size(); i++) {
            for (int j = 0; j < users.size(); j++) {
                if (resources.get(i).getUser_id().equals(users.get(j).getId())) {
                    resources.get(i).setUser(users.get(j));
                }
            }
        }

        return new PageInfo<>(resources);
    }

    @Override
    public void modifyStatus(Resource resource) throws ServiceException {
        dao.modifyStatus(resource);
    }

    @Override
    public PageInfo<Resource> queryResource() {
        List<Resource> resources =  dao.queryResource();
        List<User> users = dao.selectUserAll();
        for (int i = 0; i < resources.size(); i++) {
            for (int j = 0; j < users.size(); j++) {
                if (resources.get(i).getUser_id().equals(users.get(j).getId())) {
                    resources.get(i).setUser(users.get(j));
                }
            }
        }

        return new PageInfo<>(resources);
    }

    @Override
    public List<Course> selectCourseName() {
        List<Course> courses = dao.selectCourseName();
        return courses;
    }

    @Override
    public List<Chapter> selectChapter(String course_name) {
        List<Chapter> chapters = dao.selectChapter(course_name);
        return chapters;
    }

    @Override
    public void modifyFrontResource(String id, String title, Integer cost_type, Integer cost_number,
                                    Integer chapter_id, String original_name, String file_type,
                                    String path, String course_name, Integer file_size) {
        dao.modifyFrontResource(id,title,cost_type,cost_number,chapter_id,original_name,file_type,path,file_size);
    }

    @Override
    public String selectpath(Integer id) {
        String path = dao.selectpath(id);
        return path;
    }

    @Override
    public String selectOriginal_name(Integer resourceId) {
        String original_name = dao.selectOriginal_name(resourceId);
        return original_name;
    }

    @Override
    public void removeFrontResource(Integer id) {
        dao.removeFrontResource(id);
    }

    @Override
    public void addFrontResource( String title, Integer cost_type, Integer cost_number,
                                  Integer chapter_id, String original_name,
                                  String file_type, String path, Integer file_size) {
        dao.addFrontResource(title,cost_type,cost_number,chapter_id,original_name,file_type,path,file_size);
    }

    @Override
    public String selectname(Integer id) {
        String name = dao.selectname(id);
        return name;
    }

}
