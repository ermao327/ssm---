package com.service.impl;

import com.constant.Constant;
import com.dao.CommentDao;
import com.entity.Comment;
import com.entity.User;
import com.exception.ServiceException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.CommentService;
import com.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentDao dao;

    @Override
    public PageInfo<CommentVo> findCommentByResourceId(Integer resourceId, Integer pageNo) {
        return null;
    }

    @Override
    public Integer findPraiseCountByCommentId(Integer comment_id) {
        return dao.selectPraiseCountByCommentId(comment_id);
    }

    @Override
    public Integer addComment(Comment comment) {
        return null;
    }

    @Override
    public PageInfo<CommentVo> findCommentByStatus(Integer status, Integer pageNo) {
        return null;
    }

    @Override
    public Integer modifyCommentByStatus(Integer id, Integer status) {
        return dao.updateCommentByStatus(id,status);
    }

    @Override
    public PageInfo<CommentVo> findReviewedComments(Integer pageNo) {
        return null;
    }

    @Override
    public PageInfo<CommentVo> findComs(Integer status, Integer pageNo) {
        PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
        List<CommentVo> list = dao.selectCommentByStatus(status);


        if ( null == list){
            throw new ServiceException("没有更多评论了");
        }else {
            Iterator<CommentVo> iterator = list.iterator();
            User user = dao.selectUserByID(iterator.next().getUser_id());
            list.iterator().next().setLogin_name(user.getLogin_name());

            Integer praiseCount = dao.selectPraiseCountByCommentId(iterator.next().getId());

            list.iterator().next().setPraise_count(praiseCount);


        }
        return new PageInfo<CommentVo>(list);

    }

    @Override
    public PageInfo<CommentVo> selectComment(Integer pageNo) {
        PageHelper.startPage(pageNo,Constant.PAGE_SIZE);
        List<CommentVo>list =dao.selectComment();

        if (null == list){
            throw  new ServiceException("啥也没有");
        }else {
            Iterator<CommentVo> iterator = list.iterator();

            User user =dao.selectUserByID(iterator.next().getUser_id());

            list.iterator().next().setLogin_name(user.getLogin_name());

            Integer praiseCount = dao.selectPraiseCountByCommentId(iterator.next().getId());

            list.iterator().next().setPraise_count(praiseCount);


        }
        return new PageInfo<CommentVo>(list);
    }

    @Override
    public void sendComment(Integer resourceid, String context) {
        dao.sendComment(resourceid,context);
    }
    //评论审核条件查询
    @Override
    public PageInfo<CommentVo> findComsCondition(String login_name, Date begin_date, Date end_date, String context, Integer pageNo, Integer status) {
        PageHelper.startPage(pageNo,Constant.PAGE_SIZE);
        List<CommentVo>list = dao.findComsCondition(login_name,begin_date,end_date,context,status);
        if (null == list){
            throw new ServiceException("无可审核评论");
        }
        return new PageInfo<CommentVo>(list);
    }

    @Override
    public PageInfo<CommentVo> loadResComs(Integer resource_id, Integer pageNo) {
        PageHelper.startPage(pageNo,Constant.PAGE_SIZE);
        List<CommentVo>list =dao.loadResComs(resource_id);
        if (list ==null && resource_id != null){
            throw new ServiceException("啥也没有，别查了");
        }else {
            Iterator<CommentVo> iterator = list.iterator();
            User user = dao.selectUserByID(iterator.next().getUser_id());
            list.iterator().next().setNickname(user.getNickname());
            Integer praiseCount = dao.selectPraiseCountByCommentId(iterator.next().getId());

            list.iterator().next().setPraise_count(praiseCount);

        }
        return new PageInfo<CommentVo>(list);

    }

    @Override
    public void send(String context) {
        dao.send(context);
    }

    //评论管理条件查询
    @Override
    public PageInfo<CommentVo> findByChapterIdCondition(String login_name, Date begin_date, Date end_date, String context, Integer pageNo, Integer status) {
        PageHelper.startPage(pageNo,Constant.PAGE_SIZE);
        List<CommentVo> list = dao.findByChapterIdCondition(login_name,begin_date,end_date,context,status);
        if (null == list){
            throw new ServiceException("无评论可查询");
        }
        return new PageInfo<CommentVo>(list);
    }
}
