package service;

import Entity.Employee;
import bl.Util;
import com.sun.jdi.event.StepEvent;
import dao.EmployeeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService extends Util implements EmployeeDAO {
    @Override
    public void add(Employee employee) {
        String sql = "INSERT INTO employee (id, first_name, last_name, birthday, address_id) VALUES (?,?,?,?,?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, employee.getId());
            statement.setString(2, employee.getFirstName());
            statement.setString(3, employee.getLastName());
            statement.setDate(4, employee.getBirthday());
            statement.setLong(5, employee.getAddressID());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Employee> getAll() {
        String sql = "SELECT * FROM employee";
        List<Employee> employeeList = new ArrayList<>();

        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setBirthday(resultSet. getDate("birthday"));
                employee.setAddressID(resultSet.getLong("address_id"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public Employee getById(Long id) {
        String sql = "SELECT * FROM employee WHERE id=?";
        Employee employee = new Employee();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            employee.setId(resultSet.getLong("id"));
            employee.setFirstName(resultSet.getString("first_name"));
            employee.setLastName(resultSet.getString("last_name"));
            employee.setBirthday(resultSet. getDate("birthday"));
            employee.setAddressID(resultSet.getLong("address_id"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employee SET first_name=?, last_name=?, birthday=?, address_id=? WHERE id=?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setDate(3, employee.getBirthday());
            statement.setLong(4, employee.getAddressID());
            statement.setLong(5, employee.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void remove(Employee employee) {
        String sql = "DELETE FROM employee WHERE id=?";
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, employee.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
