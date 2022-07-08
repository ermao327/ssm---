package com.service.impl;

import com.dao.GoldPointDao666;
import com.dao.GoldPointDao666;
import com.entity.GoldPoints;
import com.entity.User;
import com.exception.DataAccessException;
import com.exception.ServiceException;
import com.service.GoldPointsService666;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service

public class GoldPointsServiceImpl666 implements GoldPointsService666 {
@Autowired
GoldPointDao666 gpD;
    @Override
    public Date getLastLoginDate(User u) {
        Date date = null;
        try {
            date = gpD.selectLastLoginDate(u.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new DataAccessException("查询最近一次签到记录失败");
        }
        return date;
    }

    @Override
    public int insertGoldPoint(GoldPoints gp) {
        int i = 0;
        try {
            i = gpD.insertGP(gp);
        } catch (Exception e) {

            e.printStackTrace();
            throw  new DataAccessException("数据插入异常");
        }
        if (i != 1){
            throw new ServiceException("插入签到记录失败");
        }
        return i;
    }

}
