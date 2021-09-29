package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.HibernateSessionFactoryUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private HibernateSessionFactoryUtil hibernateSessionFactoryUtil = new HibernateSessionFactoryUtil();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "CREATE TABLE if not exists bank_client.users_too";
        session.createSQLQuery(sql);
        transaction.commit();
        session.close();

        List<User> users = getAllUsers();
        for (User us : users) {
            System.out.println(us);
        }
        System.out.println("Create table size: " + getAllUsers().size());
    }

    @Override
    public void dropUsersTable() {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS bank_client.users_too";
        session.createSQLQuery(sql);
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query q = session.createQuery("delete User where id = " + id);
        q.executeUpdate();
        transaction.commit();
        session.close();
        System.out.println("Пользователь c id " + id + " удален");
    }

    @Override
    public List<User> getAllUsers() {

        List<User> users = new ArrayList<>();
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            users = session.createCriteria(User.class).list();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = hibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
        session.close();
    }
}
