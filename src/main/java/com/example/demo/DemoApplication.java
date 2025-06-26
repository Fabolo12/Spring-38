package com.example.demo;

import com.example.demo.models.Group;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(DemoApplication.class);
        app.setAdditionalProfiles("test");
        final ConfigurableApplicationContext context = app.run(args);
//        final ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        final UserService bean = context.getBean(UserService.class);
        System.out.println(bean.getUserDetails("12345"));
        bean.updateUserDetails("12345", "54321");
        bean.updateUserDetails(null, null);

        System.out.println("~".repeat(10));

        final User user = context.getBean(User.class);
        user.setName("John Doe");
        System.out.println(user.getName());
        System.out.println(user.getEmail());

        try {
            user.exception();
        } catch (RuntimeException ignored) {
        }

        final Group group = context.getBean(Group.class);
        group.getGroupName();
    }

}
