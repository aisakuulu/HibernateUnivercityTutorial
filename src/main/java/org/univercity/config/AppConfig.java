package org.univercity.config;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.univercity.entity.Course;
import org.univercity.entity.Instructor;
import org.univercity.entity.Lesson;
import org.univercity.entity.Task;

import java.util.Properties;

public class AppConfig {

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER,"org.postgresql.Driver");
                properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/exam_db");
                properties.put(Environment.USER, "postgres");
                properties.put(Environment.PASS, "postgres");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL9Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
                properties.put(Environment.HBM2DDL_AUTO, "create");
                configuration.setProperties(properties);
                configuration.addAnnotatedClass(Course.class);
                configuration.addAnnotatedClass(Instructor.class);
                configuration.addAnnotatedClass(Lesson.class);
                configuration.addAnnotatedClass(Task.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Connected!!!");
            } catch (HibernateException e){
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
