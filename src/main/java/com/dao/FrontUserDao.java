package com.dao;

import com.entity.GoldPoints;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface FrontUserDao {
    List<GoldPoints> selectAllRecords();

    void insertPointToGold(@Param("point_count") Integer point_count,
                                       @Param("gold_count") Integer gold_count,
                                       @Param("info1") String info1,
                                       @Param("create_date") Date create_date);

}
