package com.controller;

import com.constant.Constant;
import com.entity.User;
import com.service.impl.GoldPointsServiceImpl666;
import com.service.impl.UserGpServiceImpl666;
import com.util.AjaxResult;
import com.vo.GoldPointsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/gp666")
public class GpCtrl666 {
    @Autowired
    GoldPointsServiceImpl666 goldPointsService;
    @Autowired
    UserGpServiceImpl666 userGpService;


    //根据用户ID 查询该用户的积分金币总数
    @PostMapping("/findFrontSumByUid")
    @ResponseBody
    public AjaxResult findFrontSumByUid(HttpServletRequest request){

        User user =(User) request.getSession().getAttribute(Constant.SESSION_USER);
        GoldPointsVo goldPointsVoByUid = userGpService.findGoldPointsVoByUid(user.getId());
        AjaxResult ajaxResult = new AjaxResult(true, "查询成功", null);
        ajaxResult.setObj(goldPointsVoByUid);
        return ajaxResult;
    }
}
