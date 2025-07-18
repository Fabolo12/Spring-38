package com.example.demo;

import com.example.demo.models.Group;
import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.UUID;


@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        final SpringApplication app = new SpringApplication(DemoApplication.class);
        app.setAdditionalProfiles("test");
        final ConfigurableApplicationContext context = app.run(args);
//        final ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);

        final UserService service = context.getBean(UserService.class);
//        aopExamples(context, service);

        System.out.println("Create User:");
        final User userWithId = service.createUser(create("New User", "email@.com"));
        service.createUser(create("New User 2", null));
        service.createUser(create("User 3", null));
        System.out.println("User was created - " + userWithId.getId());
        System.out.println("Get User by ID:");
        System.out.println(service.findUserById(userWithId.getId()));
        System.out.println("Get All Users:");
        service.findAllUsers().forEach(System.out::println);
        System.out.println("Update All User Emails:");
        System.out.println("User was update - " + service.updateAllUserEmails("error@gmail.com"));
        System.out.println("Get all by email:");
        service.findAllByEmail("error@gmail.com").forEach(System.out::println);
        System.out.println("Get all By name greater than 7:");
        service.getByNameGreaterThan(7).forEach(System.out::println);
        System.out.println("Get all with pageable:");
        service.getWithSortAndPage(0, 2, "name", "asc").forEach(System.out::println);
    }

    private static User create(final String name, final String email) {
        final User user = new User();
        user.setName(name);
        user.setEmail(email);
        return user;
    }

    private static void aopExamples(
            final ConfigurableApplicationContext context,
            final UserService service
    ) {
        System.out.println(service.getUserDetails("12345"));
        service.updateUserDetails("12345", "54321");
        service.updateUserDetails(null, null);

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
