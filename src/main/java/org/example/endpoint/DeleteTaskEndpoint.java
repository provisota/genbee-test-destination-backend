package org.example.endpoint;

import org.example.service.TaskService;

public class DeleteTaskEndpoint {

    private final TaskService taskService;

    public DeleteTaskEndpoint(TaskService taskService) {
        this.taskService = taskService;
    }

    public void deleteTask(int id) {
        taskService.deleteTask(id);
    }
}