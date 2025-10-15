package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection conn;

    public UserDaoJDBCImpl() {
        this.conn = Util.getConnection();
    }

    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(45), " +
                "last_name VARCHAR(100), " +
                "age TINYINT(3))";

        try (Statement statement = conn.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        try (Statement statement = conn.createStatement()) {

            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(sql);) {


            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (PreparedStatement prepareStatement = conn.prepareStatement(sql)) {

            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();


        try (Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getByte("age"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return users;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users";

        try (Statement statement = conn.createStatement()) {

            statement.execute(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
