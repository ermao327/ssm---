package com.dao;

import com.entity.GoldPoints;
import com.entity.User;
import com.vo.GoldPointsVo;

import java.util.List;

public interface UserGpDao666 {
    public List<GoldPoints> findRecord(User user);
    public GoldPointsVo getGoldPointsVo(int uid);
    public int addGoldById(GoldPoints goldPoints);


}
