package org.univercity;

import org.univercity.config.AppConfig;
import org.univercity.dao.InstructorDao;
import org.univercity.daoImpl.CourseDaoImpl;
import org.univercity.daoImpl.InstructorDaoImpl;
import org.univercity.daoImpl.LessonDaoImpl;
import org.univercity.daoImpl.TaskDaoImpl;
import org.univercity.entity.Course;
import org.univercity.entity.Instructor;
import org.univercity.entity.Lesson;
import org.univercity.entity.Task;

import java.time.LocalDate;

public class App {

    private static final CourseDaoImpl courseDaoImpl = new CourseDaoImpl();
    private static final InstructorDaoImpl instructorDaoImpl = new InstructorDaoImpl();
    private static final LessonDaoImpl lessonImpl = new LessonDaoImpl();
    private static final TaskDaoImpl taskImpl = new TaskDaoImpl();

    public static void main(String[] args) {

        AppConfig.getSessionFactory();


        Course java = new Course("Java Middle", 2,
                LocalDate.of(2022,5,1), "java.jpg",
                "Some information about java course");
        Course javaScript = new Course("JavaScript", 3,
                LocalDate.of(2022,4,22), "javaScript.jpg",
                "Some information about JavaScript course");

        Instructor habib = new Instructor("Habib", "Abdukarimov",
                "habib@gmail.com", "0771592920");
        Instructor musab = new Instructor("Musab", "Isakov",
                "musab@gamil.com", "0779592920");

        Lesson javaCore = new Lesson("Java Core");
        Lesson softSkills = new Lesson("Soft Skills");

        Task hibernateTask = new Task(LocalDate.of(2022,9, 10),
                "Hibernate CRUD project");
        Task frontEndTask = new Task(LocalDate.of(2022,10,9),
                "React CRUD task");

        courseDaoImpl.saveCourse(java);
        courseDaoImpl.saveCourse(javaScript);

        instructorDaoImpl.saveInstructor(habib);
        instructorDaoImpl.saveInstructor(musab);

        lessonImpl.saveLesson(javaCore,1L);
        lessonImpl.saveLesson(softSkills,2L);

        taskImpl.saveTask(hibernateTask);
        taskImpl.saveTask(frontEndTask);

        instructorDaoImpl.assignInstructorToCourse(3L, 1L);
        instructorDaoImpl.assignInstructorToCourse(4L, 2L);
//        System.out.println(instructorDaoImpl.getInstructorsByCourseId(1L));

        courseDaoImpl.updateCourse(1L, java);
        courseDaoImpl.getCourseByName("Java Middle");
    }
}
