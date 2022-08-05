package org.univercity.daoImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.univercity.config.AppConfig;
import org.univercity.dao.CourseDao;
import org.univercity.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {

    SessionFactory sessionFactory = AppConfig.getSessionFactory();

    @Override
    public void saveCourse(Course course) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
            session.close();
            System.out.println("Course " + course.getCourseName() + " saved successfully!!!");
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Course getCourseById(Long id) {
        Course course = null;
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            course = session.createQuery("FROM Course " +
                            "WHERE id = ?" + id, Course.class).getSingleResult();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return course;
    }

    @Override
    public List<Course> getAllCourse() {
        // getAllCourse()(датасы боюнча сорттоп чыгаруу),
        List<Course> courses = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            courses = session.createQuery("FROM Course GROUP BY creatAt").list();
            session.getTransaction().commit();
            session.close();
            System.out.println("Found " + courses.size() + " users");
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return courses;
    }

    @Override
    public void updateCourse(Long id, Course newCourse) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            course.setCourseName(newCourse.getCourseName());
            course.setDuration(newCourse.getDuration());
            course.setCreatAt(newCourse.getCreatAt());
            course.setImageLink(newCourse.getImageLink());
            course.setDescription(newCourse.getDescription());
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void deleteCourseById(Long id) {

        // (курсту очургондо, курска assign болгон инструктор очпошу керек,
        // курсун ичиндеги сабактар очуш керек)

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            session.delete(course);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Course getCourseByName(String courseName) {
        Course course = null;
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String HQL = "SELECT course FROM Course course WHERE course.courseName = :name";
            course = (Course) session.createQuery(HQL)
                            .setParameter("name", courseName)
                                    .getSingleResult();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return course;
    }
}
