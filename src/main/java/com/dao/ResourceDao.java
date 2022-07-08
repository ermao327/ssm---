package com.dao;

import com.entity.Chapter;
import com.entity.Course;
import com.entity.Resource;
import com.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ResourceDao {
    List<Resource> selectByAll(@Param("title") String title, @Param("login_name") String login_name,
                               @Param("status") Integer status, @Param("file_type") String file_type,
                               @Param("create_start_date") Date create_start_date, @Param("create_end_date") Date create_end_date);

    List<User> selectUserAll();

    void modifyStatus(Resource resource);

    List<Resource> queryResource();

    List<Course> selectCourseName();

    List<Chapter> selectChapter(String course_name);

    void modifyFrontResource(@Param("id") String id,@Param("title") String title,@Param("cost_type") Integer cost_type,
                            @Param("cost_number") Integer cost_number,@Param("chapter_id") Integer chapter_id,
                            @Param("original_name") String original_name,@Param("file_type") String file_type,
                            @Param("path") String path,@Param("file_size") Integer file_size);

    String selectpath(Integer id);

    String selectOriginal_name(Integer resourceId);

    void removeFrontResource(Integer id);

    void addFrontResource(@Param("title") String title,@Param("cost_type") Integer cost_type,
                         @Param("cost_number") Integer cost_number,@Param("chapter_id") Integer chapter_id,
                          @Param("original_name") String original_name,
                         @Param("file_type") String file_type,@Param("path") String path,
                         @Param("file_size") Integer file_size);

    String selectname(Integer id);

}
