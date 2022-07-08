package com.service;

import com.entity.GoldPoints;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.vo.GoldPointsVo;

public interface UserGpService666 {
    public PageInfo<GoldPoints> findUserRecord(User user, Integer pageNo);
    public int updatePAG(int Uid, Integer points);
    public GoldPointsVo findGoldPointsVoByUid(int uid);
}
