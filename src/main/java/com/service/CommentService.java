package com.service;

import com.entity.Comment;
import com.github.pagehelper.PageInfo;
import com.vo.CommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CommentService {
    public PageInfo<CommentVo> findCommentByResourceId(Integer resourceId, Integer pageNo);
    public Integer findPraiseCountByCommentId(Integer commentId);
    public Integer addComment(Comment comment);
    public PageInfo<CommentVo> findCommentByStatus(Integer status,Integer pageNo);
    public Integer modifyCommentByStatus(Integer id,Integer status);

    //查询已审核评论
    public PageInfo<CommentVo> findReviewedComments(Integer pageNo);

    PageInfo<CommentVo> findComs(Integer status, Integer pageNo);

    PageInfo<CommentVo> selectComment(Integer pageNo);

    void sendComment(Integer resourceid, String context);

    //评论审核条件查询
    PageInfo<CommentVo> findComsCondition(String login_name, Date begin_date, Date end_date, String context, Integer pageNo, Integer status);

    PageInfo<CommentVo> loadResComs(Integer resource_id, Integer pageNo);

    void send(String context);

    //评论管理条件查询
    PageInfo<CommentVo> findByChapterIdCondition(String login_name, Date begin_date, Date end_date, String context, Integer pageNo, Integer status);
}
