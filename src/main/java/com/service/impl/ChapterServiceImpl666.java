package com.service.impl;

import com.constant.Constant;
import com.dao.ChapterDao666;
import com.entity.Chapter;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.service.ChapterService666;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChapterServiceImpl666 implements ChapterService666 {

    @Autowired
    private ChapterDao666 chapterDao;

    @Override
    public PageInfo<Chapter> loadChapters(int pageNo, Chapter chapter) {
        PageHelper.startPage(pageNo, Constant.PAGE_SIZE);
        List<Chapter> chapters = chapterDao.findAll(chapter);
        for (Chapter chapter1 : chapters) {
            System.out.println(chapter1);
        }
        return new PageInfo<>(chapters);
    }

    @Override
    public String checkWords(Chapter chapter, Date begin_date, Date end_date) {
        return null;
    }

    @Override
    public Chapter findChapterById(int id) {
        return chapterDao.findById(id);
    }

    @Override
    public void addChapter(Chapter chapter) {
        chapterDao.insertNewChapter(chapter);
    }

    @Override
    public int updateChapter(Chapter chapter) {
        return chapterDao.updateChapter(chapter);
    }

    @Override
    public int updateStatus(Chapter chapter) {
        return chapterDao.updateStatus(chapter);
    }
}
