package devinc.banklist;

import devinc.banklist.config.AppInitializer;
import devinc.banklist.config.HibernateConfig;
import devinc.banklist.config.WebConfig;
import devinc.banklist.dao.UserDAO;
import devinc.banklist.dao.UserDAOImpl;
import devinc.banklist.model.User;
import devinc.banklist.model.UserAccount;
import devinc.banklist.servise.UserAccountService;
import devinc.banklist.servise.UserAccountServiceImpl;
import devinc.banklist.servise.UserService;
import devinc.banklist.servise.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Starter {

    @Autowired
    static UserService userService;

    public static void main(String[] args) {


        List<User> allUsers = userService.allUsers();
        System.out.println(allUsers);

        //  List<UserAccount> allAccounts = accountService.allAccounts();
        //  System.out.println(allAccounts);

    }
}
