package org.example.taskmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskRepository {

    private Connection connection;

    public TaskRepository(Connection connection) {
        this.connection = connection;
    }

    public Task getTaskById(int id) throws SQLException {
        String query = "SELECT * FROM tasks WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            return new Task(result.getInt("id"), result.getString("name"), result.getString("description"));
        }
        return null;
    }

    public void createTask(Task task) throws SQLException {
        String query = "INSERT INTO tasks (name, description) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, task.getName());
        statement.setString(2, task.getDescription());
        statement.executeUpdate();
    }

    public void updateTask(Task task) throws SQLException {
        String query = "UPDATE tasks SET name = ?, description = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, task.getName());
        statement.setString(2, task.getDescription());
        statement.setInt(3, task.getId());
        statement.executeUpdate();
    }

    public void deleteTask(int id) throws SQLException {
        String query = "DELETE FROM tasks WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}