package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MongoConfig.class);
        MongoTemplate template = context.getBean(MongoTemplate.class);
        UserRepositoryImpl userRepository =  new UserRepositoryImpl(template);

        System.out.println(userRepository.findAllInvalidDataUsers().get(0));

        User user = new User();
        user.set_id("5faaa7c2700139301b4d8eb0");
        user.setAge(22);
        user.setName("TEST");
        user.setSurname("Surname");

       /*
        user.setAuto(new Auto("qwe", "qwe"));
        user.setCity(new City("s", "s"));

        userRepository.addUser(user);
        */

        userRepository.update(user);
        userRepository.deleteUser("5faaa7c2700139301b4d8eb0");

    }
}
