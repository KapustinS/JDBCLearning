package service;

import Entity.Address;
import bl.Util;
import dao.AddressDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressService extends Util implements AddressDAO {

    @Override
    public void add(Address address) {
        String sql = "INSERT INTO ADDRESS (ID, COUNTRY, CITY, STREET, POST_CODE) VALUES(?,?,?,?,?)";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, address.getId());
            preparedStatement.setString(2, address.getCountry());
            preparedStatement.setString(3, address.getCity());
            preparedStatement.setString(4, address.getStreet());
            preparedStatement.setString(5, address.getPostcode());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Address> getAll() {
        List<Address> addressList = new ArrayList<>();
        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS";

        try(Connection connection = getConnection();
            Statement statement = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Address address = new Address();
                address.setId(resultSet.getLong("ID"));
                address.setCountry(resultSet.getString("COUNTRY"));
                address.setCity(resultSet.getString("CITY"));
                address.setStreet(resultSet.getString("STREET"));
                address.setPostcode(resultSet.getString("POST_CODE"));

                addressList.add(address);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return addressList;
    }

    @Override
    public Address getByID(Long id) {
        String sql = "SELECT ID, COUNTRY, CITY, STREET, POST_CODE FROM ADDRESS WHERE ID=?";
        Address address = new Address();

        try(Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            address.setId(resultSet.getLong("ID"));
            address.setCountry(resultSet.getString("COUNTRY"));
            address.setCity(resultSet.getString("CITY"));
            address.setStreet(resultSet.getString("STREET"));
            address.setPostcode(resultSet.getString("POST_CODE"));

//            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void update(Address address) {
        String sql = "UPDATE ADDRESS SET COUNTRY=?, CITY=?, STREET=?, POST_CODE=? WHERE ID=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());
            statement.setString(3, address.getStreet());
            statement.setString(4, address.getPostcode());
            statement.setLong(5, address.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Address address) {
        String sql = "DELETE FROM ADDRESS WHERE ID=?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, address.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
