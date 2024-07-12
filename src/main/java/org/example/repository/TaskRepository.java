package org.example.repository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> getAllTasks();
    Optional<Task> getTaskById(int taskId);
    void createTask(Task task);
    void updateTask(Task updatedTask);
    void deleteTask(int taskId);
    void deleteAllTasks();
}