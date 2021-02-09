package devinc.banklist;

import devinc.banklist.config.AppInitializer;
import devinc.banklist.model.User;
import devinc.banklist.servise.UserService;
import devinc.banklist.servise.UserServiceImpl;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Starter2 {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppInitializer.class);
        UserService userService = context.getBean(UserServiceImpl.class);
        List<User> allUsers=userService.allUsers();
        System.out.println(allUsers);

    }
}
