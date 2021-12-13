package com.example.chatrooms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Streamable;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class LoadDb {

    private static final Logger log = LoggerFactory.getLogger(LoadDb.class);

    @Bean
    CommandLineRunner initDb(RoomRepository roomRepository, UserRepository userRepository) {
        return args -> {
            User u1 = new User("user1", "admin");
            User u2 = new User("user2", "client");
            User u3 = new User("user3", "wizard");

//            Room r1 = new Room("Movie club", new ArrayList<>());
//            Room r2 = new Room("Book club", new ArrayList<>());
            Room r1 = new Room("Movie club");
            Room r2 = new Room("Book club");

//            r1.addUser(u1);
//            r1.addUser(u2);
//            r2.addUser(u3);

            if (Streamable.of(roomRepository.findAll()).toList().size() == 0) {
                log.info("Preloading a room: " + roomRepository.save(r1));
                log.info("Preloading a room: " + roomRepository.save(r2));
            }

            if (Streamable.of(userRepository.findAll()).toList().size() == 0) {
                log.info("Preloading a user: " + userRepository.save(u1));
                log.info("Preloading a user: " + userRepository.save(u2));
                log.info("Preloading a user: " + userRepository.save(u3));
            }

            System.out.println("This is u1: " + u1);
            System.out.println("This is u2: " + u2);
            System.out.println("This is u3: " + u3);
            System.out.println("This is r: " + r1);
            System.out.println("This is r: " + r2);
        };
    }
}
