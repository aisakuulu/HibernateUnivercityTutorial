package org.univercity.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tasks")
@Getter @Setter
@NoArgsConstructor
@ToString
public class Task {

    @Id
    @GeneratedValue
    @Column(name = "task_id")
    private Long id;
    @Column(name = "dead_line")
    private LocalDate deadLine;
    @Column(name = "task")
    private String task;

    public Task(LocalDate deadLine, String task) {
        this.deadLine = deadLine;
        this.task = task;
    }
}
