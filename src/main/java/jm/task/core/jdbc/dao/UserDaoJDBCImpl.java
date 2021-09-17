package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.net.CookieHandler;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        Connection connection = util.getConnection();
        String sql = "CREATE TABLE if not exists bank_client.users_too " +
                "(id INTEGER  not NULL auto_increment, " +
                " name VARCHAR(255), " +
                " last_name VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            System.out.println("Table's created");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        Connection connection = util.getConnection();
        String sql = "DROP TABLE IF EXISTS bank_client.users_too";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            System.out.println("Table's dropped");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        Connection connection = util.getConnection();
        String sql = "INSERT INTO bank_client.users_too (name, last_name, age)" +
                " VALUES (?, ?, ?) ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        Connection connection = util.getConnection();
        String sql = "DELETE FROM bank_client.users_too WHERE ID = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("remove выполнен id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();
        Connection connection = util.getConnection();
        try {
            String sql = "SELECT * FROM bank_client.users_too";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery(sql);
            while ((rs.next())) {
                String name = rs.getString(2);
                String lastName = rs.getString(3);
                byte age = rs.getByte(4);
                users.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        dropUsersTable();
    }
}