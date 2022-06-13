package service;

import Entity.Project;
import bl.Util;
import dao.ProjectDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectService extends Util implements ProjectDAO {
    @Override
    public void add(Project project) {
        String sql = "INSERT INTO project (id, title) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setLong(1, project.getId());
            statement.setString(2, project.getTitle());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Project> getAll() {
        String sql = "SELECT * FROM project";
        List<Project> projectList = new ArrayList<>();

        try(Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Project project = new Project();
                project.setId(resultSet.getLong("id"));
                project.setTitle(resultSet.getString("title"));
                projectList.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectList;
    }

    @Override
    public Project getById(Long id) {
        String sql ="SELECT * FROM project WHERE id=?";
        Project project = new Project();

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            project.setId(resultSet.getLong("id"));
            project.setTitle(resultSet.getString("title"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return project;
    }

    @Override
    public void update(Project project) {
        String sql = "UPDATE project SET title=? WHERE id=?";

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getTitle());
            statement.setLong(2, project.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(Project project) {
        String sql = "DELETE FROM project WHERE id=?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, project.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
