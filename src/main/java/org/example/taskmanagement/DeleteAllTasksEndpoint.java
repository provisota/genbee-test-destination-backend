package org.example.taskmanagement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteAllTasksEndpoint {

    private final TaskRepository taskRepository;

    public DeleteAllTasksEndpoint(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @DeleteMapping("/tasks")
    public ResponseEntity<String> deleteAllTasks() {
        taskRepository.deleteAll();
        return new ResponseEntity<>("All tasks have been deleted", HttpStatus.OK);
    }
}