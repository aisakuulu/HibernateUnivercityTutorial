package org.univercity.daoImpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.univercity.config.AppConfig;
import org.univercity.dao.TaskDao;
import org.univercity.entity.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    SessionFactory sessionFactory = AppConfig.getSessionFactory();

    @Override
    public void saveTask(Task task) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void updateTask(Long id, Task newTask) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Task task = session.get(Task.class, id);
            task.setTask(newTask.getTask());
            task.setDeadLine(newTask.getDeadLine());
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long id) {

        List<Task> tasks = new ArrayList<>();
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String HQL = "FROM Task JOIN Lesson ON Task .id = Lesson .id = ?"+ id;
            tasks = session.createQuery(HQL).getResultList();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    @Override
    public void deleteTaskById(Long id) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Task task = session.get(Task.class, id);
            session.delete(task);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            System.out.println(e.getMessage());
        }
    }
}
