package org.example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UpdateTaskEndpoint {

    private final TaskService taskService;

    public UpdateTaskEndpoint(TaskService taskService) {
        this.taskService = taskService;
    }

    public ResponseEntity<String> updateTask(String taskId, String taskName, String taskDescription) {
        try {
            taskService.updateTask(taskId, taskName, taskDescription);
            return new ResponseEntity<>("Task updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating task: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}