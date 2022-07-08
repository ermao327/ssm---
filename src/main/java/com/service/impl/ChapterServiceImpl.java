package com.service.impl;

import com.dao.ChapterDao;
import com.entity.Chapter;
import com.github.pagehelper.PageInfo;
import com.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterDao dao;

    @Override
    public PageInfo<Chapter> loadChapters(int pageNo, Chapter chapter, Date begin_date, Date end_date) {
        return null;
    }

    @Override
    public List<Chapter> showChapterFrontByCourseId(Integer course_id) {
        return dao.showChapterFrontByCourseId(course_id);
    }

    @Override
    public String checkWords(Chapter chapter, Date begin_date, Date end_date) {
        return null;
    }

    @Override
    public void addChapter(Chapter chapter) {

    }

    @Override
    public int updateChapter(Chapter chapter) {
        return 0;
    }
}
