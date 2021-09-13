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
        String sql = "CREATE TABLE bank_client.users_too " +
                "(id INTEGER  not NULL auto_increment, " +
                " name VARCHAR(255), " +
                " last_name VARCHAR(255), " +
                " age INTEGER, " +
                " PRIMARY KEY ( id ))";

        execute(sql);
        System.out.println("Table's created");
    }

    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS bank_client.users_too";
        execute(sql);
        System.out.println("Table's dropped");
    }

    public void saveUser(String name, String lastName, byte age) {
        String columns = "(name, last_name, age)";
        String sql = "INSERT INTO bank_client.users_too " + columns +
                " VALUES ('" + name + "', '" + lastName + "', " + age + ") ";
        execute(sql);
        System.out.println("Save is INSERT");
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM bank_client.users_too WHERE ID = id";
        execute(sql);
        System.out.println("remove выполнен");
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection connection;
        Statement statement;
        connection = util.getConnection();
        String sql = "SELECT * FROM bank_client.users_too";
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while ((rs.next())) {
                String name = rs.getString(2);
                String lastName = rs.getString(3);
                byte age = rs.getByte(4);
                users.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        dropUsersTable();
    }

    private void execute(String sql) {
        Connection connection;
        Statement statement;
        connection = util.getConnection();
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
