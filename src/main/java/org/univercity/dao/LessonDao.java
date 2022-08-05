package org.univercity.dao;

import org.univercity.entity.Lesson;

import java.util.List;

public interface LessonDao {

    void saveLesson(Lesson lesson, Long courseId);

    void updateLesson(Long id, Lesson newLesson);

    Lesson getLessonByID(Long id);

    List<Lesson> getLessonsByCourseId(Long courseId);


}
