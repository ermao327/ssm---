package com.dao;

import com.entity.Chapter;

import java.util.List;


public interface ChapterDao666 {
    public List<Chapter> findAll(Chapter chapter);
    void insertNewChapter(Chapter chapter);
    int updateChapter(Chapter chapter);
    int updateStatus(Chapter chapter);
    public Chapter findById(int id);
}
