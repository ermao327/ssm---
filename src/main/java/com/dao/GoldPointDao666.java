package com.dao;

import com.entity.GoldPoints;

import java.util.Date;


public interface GoldPointDao666 {
    public Date selectLastLoginDate(int user_id);
    public  int insertGP(GoldPoints gp);

}
