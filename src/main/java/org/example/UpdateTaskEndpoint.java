package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateTaskEndpoint {

    private final TaskService taskService;

    @Autowired
    public UpdateTaskEndpoint(TaskService taskService) {
        this.taskService = taskService;
    }

    @PutMapping("/tasks")
    public ResponseEntity<Void> updateTask(@RequestBody Task updatedTask) {
        taskService.updateTask(updatedTask);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}