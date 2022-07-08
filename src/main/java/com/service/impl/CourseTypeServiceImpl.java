package com.service.impl;

import com.constant.Constant;
import com.dao.CourseTypeDao;
import com.entity.CourseType;
import com.entity.User;
import com.exception.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CourseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseTypeServiceImpl implements CourseTypeService {
    @Autowired
    CourseTypeDao dao;

    @Override
    public PageInfo<CourseType> findBackCourseTypeByAll(Integer pageNo,Integer parent_id) throws ServiceException {
        //开启分页插件的分页功能
        PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
        List<CourseType>  courseTypes = dao.selectByAll(parent_id);

        if (null == courseTypes) {
            throw new ServiceException("没有更多课程信息了...");
        }
        return new PageInfo<CourseType>(courseTypes);
    }

    @Override
    public void modifyStatus(CourseType courseType) throws ServiceException {
        //修改课程状态
        dao.updateStatus(courseType);
    }

    @Override
    public void modifyCourseTypeName(CourseType courseType) throws ServiceException {
        //修改课程类别名称
        dao.updateCourseTypeName(courseType);
    }

    @Override
    public void addCourseType(CourseType courseType) throws ServiceException {
        //判断课程类别是否存在
        CourseType c = dao.selectByName(courseType.getType_name());
        if (c != null) {
            //如果名称存在，直接覆盖解决(根据名称查找，有就覆盖，改成id查询就是名称所在id存在，其他随便改都不会更新也不会插入)
            dao.updateCourseType(courseType);
        }
        else
        {
            //添加课程类别
            dao.insertCourseType(courseType);
        }
    }

    @Override
    public List<CourseType> findCourseTypeById(Integer id) throws ServiceException {
        List<CourseType> courseTypes = dao.selectCourseTypeById(id);
        if (null == courseTypes) {
            throw new ServiceException("没有更多课程信息了...");
        }
        return courseTypes;
    }


    @Override
    public List<CourseType> selectCourseTypeByParentId() throws ServiceException {
        List<CourseType> courseTypes = dao.selectCourseTypeByParentId();

        return courseTypes;
    }

    @Override
    public Set<CourseType> selectTypeChildren(Integer id) {
        Set<CourseType> courseTypes = dao.selectTypeChildren(id);

        return courseTypes;
    }

}
