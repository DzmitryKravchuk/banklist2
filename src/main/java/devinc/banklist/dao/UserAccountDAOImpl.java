package devinc.banklist.dao;



import devinc.banklist.model.User;
import devinc.banklist.model.UserAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserAccountDAOImpl implements UserAccountDAO {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    // public static final String SQL_SELECT_ALL_ACCOUNTS = "SELECT * FROM user_account";
    // public static final String SQL_DELETE_ALL_ACCOUNTS = "DELETE FROM user_account";
    // public static final String SQL_SELECT_ACCOUNTS_OF_ONE_USER = "SELECT id, account FROM user_account WHERE user_id =?"; // specific method (optional)
    // public static final String SQL_SELECT_ACCOUNT_BY_ID = "SELECT account, user_id FROM user_account WHERE id =?";
    // public static final String SQL_DELETE_ACCOUNT_BY_ID = "delete from user_account where id=?";
    // public static final String SQL_CREATE_NEW_ACCOUNT = "INSERT INTO user_account (account, user_id) VALUES(?,?)";
    // public static final String SQL_UPDATE_ACCOUNT = "UPDATE user_account SET account=?, user_id=? WHERE id=?";

    @Override
    public List<UserAccount> allAccounts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from UserAccount").list();
    }

    @Override
    public List<UserAccount> findAccountsForUser(Integer userId) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from UserAccount where user.id = :param");
        query.setParameter("param", userId);
        return query.list();
    }

    @Override
    public void add(UserAccount userAccount) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(userAccount);
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete UserAccount where id = :param");
        query.setParameter("param", id);
        query.executeUpdate();
    }

    @Override
    public void deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("delete from UserAccount ");
        query.executeUpdate();
    }

    @Override
    public void edit(UserAccount userAccount) {
        Session session = sessionFactory.getCurrentSession();
        session.update(userAccount);
    }

    @Override
    public UserAccount getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(UserAccount.class, id);
    }
}
