package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository {
    private List<Task> _tasks = new ArrayList<>();

    public List<Task> get_all_tasks() {
        return new ArrayList<>(_tasks);
    }

    public Task get_task_by_id(int task_id) {
        return _tasks.stream()
                .filter(task -> task.getId() == task_id)
                .findFirst()
                .orElse(null);
    }

    public Task create_task(Task task) {
        _tasks.add(task);
        return task;
    }

    public Task update_task(Task updatedTask) {
        Optional<Task> existingTask = _tasks.stream()
                .filter(task -> task.getId() == updatedTask.getId())
                .findFirst();
        if (existingTask.isPresent()) {
            int index = _tasks.indexOf(existingTask.get());
            _tasks.set(index, updatedTask);
            return updatedTask;
        }
        return null;
    }

    public void delete_task(int task_id) {
        _tasks.removeIf(task -> task.getId() == task_id);
    }

    public void delete_all_tasks() {
        _tasks.clear();
    }
}