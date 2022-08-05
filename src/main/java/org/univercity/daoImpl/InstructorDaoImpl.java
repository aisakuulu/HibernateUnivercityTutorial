package org.univercity.daoImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.univercity.config.AppConfig;
import org.univercity.dao.InstructorDao;
import org.univercity.entity.Course;
import org.univercity.entity.Instructor;

import java.util.ArrayList;
import java.util.List;

public class InstructorDaoImpl implements InstructorDao {

    SessionFactory sessionFactory = AppConfig.getSessionFactory();

    @Override
    public void saveInstructor(Instructor instructor) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(instructor);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateInstructor(Long id, Instructor newInstructor) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, id);
            instructor.setFirstName(newInstructor.getFirstName());
            instructor.setLastName(newInstructor.getLastName());
            instructor.setEmail(newInstructor.getEmail());
            instructor.setPhoneNumber(newInstructor.getPhoneNumber());
            session.getTransaction().commit();
            session.close();
            System.out.println("Instructor " + newInstructor.getFirstName() + " has been updated!!!");
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Instructor getInstructorById(Long id) {
        Instructor instructor = null;
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            instructor = session.createQuery("FROM Instructor " +
                    "WHERE id = ?" + id, Instructor.class).getSingleResult();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return instructor;
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long id) {

        // (тиешелуу курстун инструкторлорун чыгарып беруу)

        List<Instructor> instructors = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String HQL = "FROM Instructor JOIN Course ON Instructor .id = Course .id = ?"+ id;
            instructors = session.createQuery(HQL).getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return instructors;
    }

    @Override
    public void deleteInstructorById(Long id) {

        // (инструктор очкондо, инструкторго тиешелуу курс очпошу керек)

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, id);
            session.delete(instructor);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void assignInstructorToCourse(Long instructorId, Long courseID) {

        // (инструкторду курска кошуп коюу(назначить)

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Instructor instructor = session.get(Instructor.class, instructorId);
            Course course = session.get(Course.class, courseID);
            course.addInstructor(instructor);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }
}
