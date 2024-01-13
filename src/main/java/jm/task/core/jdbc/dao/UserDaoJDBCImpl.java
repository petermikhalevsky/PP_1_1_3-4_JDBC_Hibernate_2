package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public static final String CREATE = "CREATE TABLE IF NOT EXISTS USERS ";
    private static final String TABLE = "(id INTEGER not NULL AUTO_INCREMENT, name VARCHAR(30), lastName VARCHAR(30), age INTEGER, PRIMARY KEY ( id ))";

    public UserDaoJDBCImpl() {

    }
    private final Connection connection = Util.getConnection();
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute(CREATE + TABLE);
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS USERS");
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement =
                               connection.prepareStatement("INSERT INTO USERS(name, lastName, age) VALUES(?, ?, ?)")) {
                                          preparedStatement.setString(1, name);
                                          preparedStatement.setString(2, lastName);
                                          preparedStatement.setByte(3, age);
                                          preparedStatement.executeUpdate();
            System.out.printf("User %s added\n", name);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("DELETE FROM USERS WHERE id = %d;", id));
        } catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("DELETE FROM users;");
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                list.add(user);

            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return list;
    }

}
