package com.service.impl;

import com.dao.UserGpDao666;
import com.entity.GoldPoints;
import com.entity.User;
import com.github.pagehelper.PageInfo;
import com.service.UserGpService666;
import com.vo.GoldPointsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGpServiceImpl666 implements UserGpService666 {

    @Autowired
    UserGpDao666 userGpDao;
    @Override
    public PageInfo<GoldPoints> findUserRecord(User user, Integer pageNo) {
        return null;
    }

    @Override
    public int updatePAG(int Uid, Integer points) {
        return 0;
    }

    @Override
    public GoldPointsVo findGoldPointsVoByUid(int uid) {
        GoldPointsVo goldPointsVo = userGpDao.getGoldPointsVo(uid);
        return goldPointsVo;
    }
}
