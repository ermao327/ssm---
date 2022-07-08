package com.controller;

import com.entity.Chapter;
import com.service.ChapterService;
import com.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    ChapterService chapterService;

    @RequestMapping("/showChapterFront")
    @ResponseBody
    public AjaxResult showChapterFront(HttpServletRequest request){
        AjaxResult result = new AjaxResult(true,"查询成功",null);

        int course_id = Integer.valueOf(request.getParameter("course_id"));
        List<Chapter> list = chapterService.showChapterFrontByCourseId(course_id);
        System.out.println(list.get(0).getResources());

        try{
            result.setObj(list);
        }catch(Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
