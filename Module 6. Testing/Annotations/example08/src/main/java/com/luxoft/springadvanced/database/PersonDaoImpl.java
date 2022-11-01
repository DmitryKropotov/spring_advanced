package com.luxoft.springadvanced.database;

import com.luxoft.springadvanced.Person;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonDaoImpl implements PersonDao {

    private Connection connection;

    public PersonDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Person person) {
        String sql = "INSERT INTO PERSONS (ID, NAME) VALUES (?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, person.getIdentifier());
            statement.setString(2, person.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(String id, String name) {
        String sql = "UPDATE PERSONS SET NAME = ? WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Person person) {
        String sql = "DELETE FROM PERSONS WHERE ID = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, person.getIdentifier());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Person getById(String id) {
        String sql = "SELECT * FROM PERSONS WHERE ID = ?";
        Person person = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                person = new Person(resultSet.getString(1), resultSet.getString(2));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return person;
    }
}
