package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateSessionFactoryUtil;
import jm.task.core.jdbc.util.Util;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util util = new Util();
        util.getConnection();

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        userDaoJDBC.createUsersTable();

        User user = new User("Bob", "Jones", (byte) 39);
        userDaoJDBC.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println(user);

        User user2 = new User("Anna", "Ivanko", (byte) 35);
        userDaoJDBC.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println(user2);

        User user3 = new User("Paul", "Green", (byte) 22);
        userDaoJDBC.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println(user3);

        User user4 = new User("Olga", "Iks", (byte) 28);
        userDaoJDBC.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println(user4);

        List<User> users = userDaoJDBC.getAllUsers();
        for (User us : users) {
            System.out.println(us);
        }

        userDaoJDBC.dropUsersTable();

    }
}
