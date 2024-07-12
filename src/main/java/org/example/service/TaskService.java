package org.example.service;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private Map<Integer, Task> taskRepository = new HashMap<>();

    public void createTask(Task task) {
        taskRepository.put(task.getId(), task);
    }

    public void updateTask(Task updatedTask) {
        if (taskRepository.containsKey(updatedTask.getId())) {
            taskRepository.replace(updatedTask.getId(), updatedTask);
        }
    }

    public void deleteTask(int id) {
        taskRepository.remove(id);
    }
}