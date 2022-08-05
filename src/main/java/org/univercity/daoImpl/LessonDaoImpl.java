package org.univercity.daoImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.univercity.config.AppConfig;
import org.univercity.dao.LessonDao;
import org.univercity.entity.Course;
import org.univercity.entity.Lesson;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LessonDaoImpl implements LessonDao {

    SessionFactory sessionFactory = AppConfig.getSessionFactory();

    @Override
    public void saveLesson(Lesson lesson, Long courseId) {

        // (Lesson сакталып жатканда кандайдыр бир курска сакталуусу керек)

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Course course = session.get(Course.class, courseId);
            session.save(lesson);
            course.addLesson(lesson);
            session.getTransaction().commit();
            session.close();
        } catch (HeadlessException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateLesson(Long id, Lesson newLesson) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Lesson lesson = session.get(Lesson.class, id);
            lesson.setName(newLesson.getName());
            session.getTransaction().commit();
            session.close();
        } catch (HeadlessException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Lesson getLessonByID(Long id) {
        Lesson lesson = null;
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            lesson = session.createQuery("FROM Lesson " +
                    "WHERE id = ?" + id, Lesson.class).getSingleResult();
            session.getTransaction().commit();
            session.close();
        } catch (HeadlessException e){
            System.out.println(e.getMessage());
        }
        return lesson;
    }

    @Override
    public List<Lesson> getLessonsByCourseId(Long courseId) {

        // (курска тиешелуу сабактарды чыгаруу)

        List<Lesson> lessons = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "FROM Lesson JOIN Course ON Lesson .id = Course .id = ?"+ courseId;
            lessons = session.createQuery(hql).list();
            session.getTransaction().commit();
            session.close();
        } catch (HeadlessException e){
            System.out.println(e.getMessage());
        }
        return lessons;
    }
}
