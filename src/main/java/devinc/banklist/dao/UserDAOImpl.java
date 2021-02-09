package devinc.banklist.dao;


import devinc.banklist.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // public static final String SQL_SELECT_ALL_USERS = "SELECT * FROM user";
    // public static final String SQL_DELETE_ALL_USERS = "DELETE FROM user";
    // public static final String SQL_SELECT_USER_BY_ID = "SELECT sur_name, name FROM user WHERE id =?";
    // public static final String SQL_DELETE_USER_BY_ID = "delete from user where id=?";
    // public static final String SQL_CREATE_NEW_USER = "INSERT INTO user (name, sur_name) VALUES(?,?)";
    //  public static final String SQL_UPDATE_USER = "UPDATE user SET name=?, sur_name=? WHERE id=?";


    @Override
    public List<User> allUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list();
    }

    @Override
    public void add(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete User where id = :param");
        query.setParameter("param", id);
        query.executeUpdate();
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from User");
        query.executeUpdate();
    }


    @Override
    public void edit(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    @Override
    public User getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }
}
