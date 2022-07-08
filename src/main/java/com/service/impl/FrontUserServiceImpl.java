package com.service.impl;

import com.constant.Constant;
import com.dao.FrontUserDao;
import com.entity.GoldPoints;
import com.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.FrontUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FrontUserServiceImpl implements FrontUserService {

    @Autowired
    FrontUserDao dao;

    @Override
    public PageInfo<GoldPoints> findAllRecords(Integer pageNo) {
        //开启分页插件的分页功能
        PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
        List<GoldPoints> goldPoints = dao.selectAllRecords();
        return new PageInfo<>(goldPoints);
    }

    @Override
    public PageInfo<GoldPoints> findAllRecordsInfo() {
        List<GoldPoints> goldPoints = dao.selectAllRecords();
        return new PageInfo<>(goldPoints);
    }

    @Override
    public void insertPointToGold(Integer point_count,Integer gold_count,
                                                  String info1, Date create_date) {
        dao.insertPointToGold(point_count,gold_count,info1,create_date);
    }
}
