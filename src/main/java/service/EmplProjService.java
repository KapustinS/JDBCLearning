package service;

import Entity.EmplProj;
import bl.Util;
import com.mysql.cj.jdbc.PreparedStatementWrapper;
import dao.EmplProjDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmplProjService extends Util implements EmplProjDAO {
    @Override
    public void add(EmplProj emplProj){
        String sql = "INSERT INTO empl_proj (employee_id, project_id) VALUES (?, ?)";

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, emplProj.getEmployeeID());
            statement.setLong(2, emplProj.getProjectID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<EmplProj> getAll(){
        String sql = "SELECT * FROM empl_proj";
        List<EmplProj> emplProjList = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                EmplProj emplProj = new EmplProj();
                emplProj.setEmployeeID(resultSet.getLong("employee_id"));
                emplProj.setProjectID(resultSet.getLong("project_id"));

                emplProjList.add(emplProj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emplProjList;
    }

    @Override
    public EmplProj getByEmployeeIdAndProjectId(Long employeeId, Long projectId){
        String sql = "SELECT * FROM empl_proj WHERE employee_id=? AND project_id=?";
        EmplProj emplProj = new EmplProj();

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, employeeId);
            statement.setLong(2, projectId);
            ResultSet resultSet = statement.executeQuery();

            emplProj.setEmployeeID(resultSet.getLong("employee_id"));
            emplProj.setEmployeeID(resultSet.getLong("project_id"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return emplProj;
    }

    @Override
    public void update(EmplProj emplProj){
        String sql = "UPDATE empl_proj SET employee_id=?, project_id=?";

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, emplProj.getEmployeeID());
            statement.setLong(2, emplProj.getProjectID());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(EmplProj emplProj){
        String sql="DELETE FROM empl_proj WHERE employee_id=? AND project_id=?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, emplProj.getEmployeeID());
            statement.setLong(2, emplProj.getProjectID());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
