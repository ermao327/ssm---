package com.service;

import com.entity.Chapter;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

public interface ChapterService {
    PageInfo<Chapter> loadChapters( int pageNo,Chapter chapter, Date begin_date,Date end_date);
    List<Chapter> showChapterFrontByCourseId(Integer course_id);
    String checkWords(Chapter chapter,Date begin_date,Date end_date);
    void addChapter(Chapter chapter);
    int updateChapter(Chapter chapter);
}
