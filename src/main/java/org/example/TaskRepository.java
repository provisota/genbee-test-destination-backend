package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements AutoCloseable {

    private Connection connection;

    public TaskRepository(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public List<Task> getAllTasks() throws SQLException {
        List<Task> tasks = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks")) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Task task = new Task(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getBoolean("completed")
                    );
                    tasks.add(task);
                }
            }
        }
        return tasks;
    }

    public Task getTaskById(int taskId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM tasks WHERE id = ?")) {
            statement.setInt(1, taskId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new Task(
                            resultSet.getInt("id"),
                            resultSet.getString("description"),
                            resultSet.getBoolean("completed")
                    );
                }
            }
        }
        return null;
    }

    public void createTask(Task task) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO tasks (description, completed) VALUES (?, ?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, task.getDescription());
            statement.setBoolean(2, task.isCompleted());
            statement.executeUpdate();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    task.setId(resultSet.getInt(1));
                }
            }
        }
    }

    public void updateTask(Task task) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE tasks SET description = ?, completed = ? WHERE id = ?")) {
            statement.setString(1, task.getDescription());
            statement.setBoolean(2, task.isCompleted());
            statement.setInt(3, task.getId());
            statement.executeUpdate();
        }
    }

    public void deleteTask(int taskId) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM tasks WHERE id = ?")) {
            statement.setInt(1, taskId);
            statement.executeUpdate();
        }
    }
}

class Task {

    private int id;
    private String description;
    private boolean completed;

    public Task(int id, String description, boolean completed) {
        this.id = id;
        this.description = description;
        this.completed = completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}