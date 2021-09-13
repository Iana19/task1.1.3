package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util util = new Util();            //
        util.getConnection();              //

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();     //
        userDaoJDBC.dropUsersTable();       //

        userDaoJDBC.saveUser("Bob", "Jones", (byte) 39);
        userDaoJDBC.getAllUsers();
        userDaoJDBC.removeUserById(4);
    }
}
