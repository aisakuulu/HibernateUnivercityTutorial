package org.univercity.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lessons")
@NoArgsConstructor
@Getter @Setter
@ToString
public class Lesson {

    @Id
    @GeneratedValue
    @Column(name = "lesson_id")
    private Long id;
    @Column(name = "lesson_name")
    private String name;

    public Lesson(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

}
