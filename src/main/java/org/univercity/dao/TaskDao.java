package org.univercity.dao;

import org.univercity.entity.Task;

import java.util.List;

public interface TaskDao {

    void saveTask(Task task);

    void updateTask(Long id, Task newTask);

    List<Task> getAllTaskByLessonId(Long id);

    void deleteTaskById(Long id);
}
