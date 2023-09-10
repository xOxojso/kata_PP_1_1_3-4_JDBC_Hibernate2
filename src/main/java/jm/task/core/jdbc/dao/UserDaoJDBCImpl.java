package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        try (PreparedStatement statement
                     = Util.getConnection().prepareStatement("CREATE TABLE User(\n" +
                "    id int,\n" +
                "    name VARCHAR(100),\n" +
                "    lastname VARCHAR(100),\n" +
                "    age int\n" +
                ");")) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement statement
                     = Util.getConnection().prepareStatement("DROP TABLE IF EXISTS User")) {
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement
                     = Util.getConnection().prepareStatement("INSERT INTO User values (1,?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);

            statement.executeUpdate();
            System.out.printf("%s добавлен в базу данных%n", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement
                     = Util.getConnection().prepareStatement("DELETE FROM User WHERE id = ?")) {
            statement.setLong(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            String select = "SELECT * FROM User";
            ResultSet resultSet = statement.executeQuery(select);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        try (PreparedStatement statement
                     = Util.getConnection().prepareStatement("DELETE FROM User")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
