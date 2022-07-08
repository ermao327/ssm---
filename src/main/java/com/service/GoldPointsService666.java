package com.service;

import com.entity.GoldPoints;
import com.entity.User;

import java.util.Date;

public interface GoldPointsService666 {
    public Date getLastLoginDate(User u);
    public int insertGoldPoint(GoldPoints gp);
}
