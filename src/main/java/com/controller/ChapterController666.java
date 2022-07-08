package com.controller;

import com.entity.Chapter;
import com.github.pagehelper.PageInfo;
import com.service.impl.ChapterServiceImpl;
import com.service.impl.ChapterServiceImpl666;
import com.util.AjaxResult;
import com.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

//章节
@RestController
@RequestMapping("/chapter666")
public class ChapterController666 {

    @Autowired
    private ChapterServiceImpl666 chapterService;

    @RequestMapping("/loadChapter")
    public AjaxResult loadChapter(HttpServletRequest request, HttpServletResponse response){
         //       进入章节
        String pageNo = request.getParameter("pageNo");
        int pageNum = 0;
        if (pageNo != null && !"".equals(pageNo)){
            pageNum = Integer.valueOf(pageNo);
        }

        String course_id = request.getParameter("course_id");
        int courseId = 0;
        if (course_id != null && !"".equals(course_id)){
            courseId = Integer.valueOf(course_id);
        }

        String begin_date = request.getParameter("begin_date");
        String end_date = request.getParameter("end_date");
        Date beginDate = null;
        Date endDate = null;
        if (begin_date != null && !"".equals(begin_date)){
          //     开始格式化
            beginDate = DateUtil.startDate(begin_date);
          //   格式化成功
        }
         //      起始日期初始化成功
        if (end_date != null && !"".equals(end_date)){
         //      开始格式化
            endDate = DateUtil.endDate(end_date);
           //  格式化成功
        }
         //    结束日期初始化结束
        String title = request.getParameter("title");
        String info = request.getParameter("info");
        String statusId = request.getParameter("status");
        Integer status = null;
        if (statusId != null && !"".equals(statusId)){
             status = Integer.valueOf(statusId);
        }

        Chapter chapter = new Chapter(courseId, beginDate, endDate, title, info, status);
        System.out.println("chapter="+chapter);
        PageInfo<Chapter> chapterPageInfo = chapterService.loadChapters(pageNum, chapter);
        System.out.println("chapterPageInfo="+chapterPageInfo);
        return AjaxResult.isOk(chapterPageInfo);
    }


      //回显即将修改的记录信息
    @RequestMapping("/showToModify")
    public AjaxResult showToModify(String id){
        Chapter chapterById = chapterService.findChapterById(Integer.valueOf(id));
        return AjaxResult.isOk(chapterById);
    }


     // 修改章节信息
    @RequestMapping("/modifyChapter")
    public AjaxResult modifyChapter(String title, String info, String id){

        Chapter chapterById = chapterService.findChapterById(Integer.valueOf(id));
        chapterById.setTitle(title);
        chapterById.setInfo(info);
        int i = chapterService.updateChapter(chapterById);
        if (i > 0){
            return AjaxResult.isOk("修改成功");
        }
        return AjaxResult.isFail("修改失败");
    }


     //章节管理的启用禁用
    @RequestMapping("/modifyChapterX")
    public AjaxResult modifyChapterX(String id, String status){
//      进入启用，禁用
        Chapter chapterById = chapterService.findChapterById(Integer.valueOf(id));
        chapterById.setStatus(Integer.valueOf(status));
        int i = chapterService.updateStatus(chapterById);
        if (i > 0){
            return AjaxResult.isOk("修改成功");
        }
        return AjaxResult.isFail("修改失败");
    }


     //添加章节
    @RequestMapping("/addChapter")
    public AjaxResult addChapter(String title, String info, String course_id){
        Chapter chapter = new Chapter(title, Integer.valueOf(course_id), info, new Date(), 1);
        try {
            chapterService.addChapter(chapter);
            return AjaxResult.isOk();
        } catch (Exception e) {
            return AjaxResult.isFail();
        }
    }
}
