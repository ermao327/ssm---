package com.service;

import com.entity.GoldPoints;
import com.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.Date;

public interface FrontUserService {
    PageInfo<GoldPoints> findAllRecords(Integer pageNo);
    PageInfo<GoldPoints> findAllRecordsInfo();
    void insertPointToGold(Integer point_count, Integer gold_count,
                                           String info1, Date create_date);

}
