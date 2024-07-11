package org.example;

import java.util.Optional;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task updatedTask) {
        return taskRepository.update(updatedTask);
    }

    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }
}