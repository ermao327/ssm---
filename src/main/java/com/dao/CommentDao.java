package com.dao;

import com.entity.Comment;
import com.entity.Praise;
import com.entity.User;
import com.vo.CommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface CommentDao {
    public List<CommentVo> selectCommentByResourceId(Integer resourceId);
    public User selectUserByID(Integer id);
    public Integer selectPraiseCountByCommentId(Integer commentId);
    public Integer insertComment(Comment comment);
    //查询待审核评论
    public List<CommentVo> selectCommentByStatus(Integer status);

    public Integer updateCommentByStatus(@Param("id") Integer id, @Param("status") Integer status);
    //查询已审核评论
    public List<CommentVo> selectReviewedComments();

    List<CommentVo> selectComment();

    void sendComment(@Param("resourceid")Integer resourceid, @Param("context")String context);

    List<CommentVo> findComsCondition(@Param("login_name")String login_name, @Param("begin_date")Date begin_date, @Param("end_date")Date end_date, @Param("context")String context, @Param("status")Integer status);

    List<CommentVo> loadResComs(Integer resource_id);

    void send(String context);

    List<CommentVo> findByChapterIdCondition(@Param("login_name")String login_name, @Param("begin_date")Date begin_date, @Param("end_date")Date end_date, @Param("context")String context, @Param("status")Integer status);
}
