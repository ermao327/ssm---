package com.controller;

import com.github.pagehelper.PageInfo;
import com.service.CommentService;
import com.util.AjaxResult;
import com.util.DateUtil;
import com.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService service;

    //前台评论加载
    @RequestMapping("loadResComs")
    @ResponseBody
    public AjaxResult loadResComs(@RequestParam("resource_id")Integer resource_id, @RequestParam("pageNo")Integer pageNo){
        AjaxResult result = new AjaxResult(true,"查询评论",null);
        try {
            PageInfo<CommentVo> info = service.loadResComs(resource_id,pageNo);
            result.setObj(info);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }



    @RequestMapping("/toggle")
    @ResponseBody
    public void togglecomment(Integer id,Integer status){
        service.modifyCommentByStatus(id,status);
    }


    //发表评论
    @RequestMapping("sendComs")
    @ResponseBody
    public AjaxResult sendComs(HttpServletRequest request) {
        AjaxResult result = new AjaxResult(true, "发布成功", null);
        Integer resourceid = 1;
        if (request.getParameter("resource_id") !=null || request.getParameter("resource_id").equals("")){
            resourceid =Integer.valueOf(request.getParameter("resource_id"));


        }

        String context = request.getParameter("context");

        service.sendComment(resourceid, context);

        return result;
    }
    //评论审核条件查询
    @RequestMapping("findComs")
    @ResponseBody
    public AjaxResult findComsCondition(HttpServletRequest request){
        AjaxResult result = new AjaxResult(true,"查询成功",null);
        String login_name = request.getParameter("login_name");
        Date begin_date = DateUtil.startDate("begin_date");
        Date end_date = DateUtil.startDate("end_date");
        String context = request.getParameter("context");
        Integer pageNo =Integer.valueOf(request.getParameter("pageNo"));
        Integer status = Integer.valueOf(request.getParameter("status"));
        try {
            PageInfo<CommentVo> info = service.findComsCondition(login_name,begin_date,end_date,context,pageNo,status);
            result.setObj(info);
        }catch (Exception e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return  result;
    }

    //评论管理条件查询
    @RequestMapping("findByChapterId")
    @ResponseBody
    public AjaxResult findByChapterIdCondition(HttpServletRequest request){
        AjaxResult result = new AjaxResult(true,"查询成功",null);
        Integer pageNo = Integer.valueOf(request.getParameter("pageNo"));
        String login_name = request.getParameter("login_name");
        Date begin_date = DateUtil.startDate("begin_date");
        Date end_date = DateUtil.startDate("end_date");
        String context = request.getParameter("context");
        Integer status = Integer.valueOf(request.getParameter("status"));

        try {
            PageInfo<CommentVo> info = service.findByChapterIdCondition(login_name,begin_date,end_date,context,pageNo,status);
            result.setObj(info);
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;

    }




    //前台 点赞 举报
    @RequestMapping("/getPraCount")
    @ResponseBody
    public AjaxResult getPraCount(HttpServletRequest request){

        Integer comment_id = Integer.valueOf(request.getParameter("comment_id"));
        Integer praiseCount= service.findPraiseCountByCommentId(comment_id);
        AjaxResult result = new AjaxResult(true,"点赞成功",praiseCount);

        return result;
    }
}
