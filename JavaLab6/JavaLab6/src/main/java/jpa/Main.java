package jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UsersRepository usersRepository = context.getBean(UsersRepository.class);
        AutoRepository autoRepository = context.getBean(AutoRepository.class);
        CitiesRepository citiesRepository = context.getBean(CitiesRepository.class);

        /*
        Auto auto =  Auto.builder()
                .model("kia")
                .number("123")
                .build();

        City city = City.builder()
                .city("Kazan")
                .build();

        auto = autoRepository.save(auto);
        city = citiesRepository.save(city);

        User user = User.builder()
                .name("Test1")
                .surname("Test2")
                .age(23)
                .auto(Collections.singletonList(
                        auto
                       ))
                .city(city)
                .sex("Man")
                .build();

        usersRepository.save(user);

         */
/*
        List<User> userList = usersRepository.findAllWithShortName(3);
        System.out.println(userList);

 */

        List<User> userList = usersRepository.findByAutoCountAndCity(1, true);
        System.out.println(userList);
    }
}
