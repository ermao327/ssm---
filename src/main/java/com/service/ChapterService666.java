package com.service;

import com.entity.Chapter;
import com.github.pagehelper.PageInfo;

import java.util.Date;

public interface ChapterService666 {
    PageInfo<Chapter> loadChapters(int pageNo, Chapter chapter);
    String checkWords(Chapter chapter, Date begin_date, Date end_date);
    Chapter findChapterById(int id);
    void addChapter(Chapter chapter);
    int updateChapter(Chapter chapter);
    int updateStatus(Chapter chapter);
}
