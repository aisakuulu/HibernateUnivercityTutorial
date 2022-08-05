package org.univercity.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Course {

    @Id
    @GeneratedValue()
    @Column(name = "course_id", nullable = false)
    private Long id;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "duration")
    private int duration;
    @Column(name = "created_date")
    private LocalDate creatAt;
    @Column(name = "image_link")
    private String imageLink;
    @Column(name = "description")
    private String description;

    public Course(String courseName, int duration, LocalDate creatAt, String imageLink, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.creatAt = creatAt;
        this.imageLink = imageLink;
        this.description = description;
    }

    @ManyToMany(fetch = FetchType.LAZY
            ,cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "course_insturctor"
            , joinColumns = @JoinColumn(name = "course_id")
            , inverseJoinColumns = @JoinColumn(name = "instructor_id"))
    private List<Instructor> instructors;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "course")
    private List<Lesson> lessons;

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
    }

    public void addLesson(Lesson lesson) {
        lessons.add(lesson);
    }
}
