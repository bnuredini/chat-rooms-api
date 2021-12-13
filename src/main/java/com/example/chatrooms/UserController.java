package com.example.chatrooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoomRepository roomRepo;

    @GetMapping("/users")
    List<User> getUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id) {
        return userRepo.findById(id)
                       .orElseThrow(() -> new UserNotFoundException("Couldn't find a user with ID " + id));
    }

    @PostMapping("/users")
    User createUser(@RequestBody User u) {
        return userRepo.save(u);
    }

    @PostMapping("/users/{userId}/rooms/{roomId}")
    User addUserToRoom(@PathVariable Long userId, @PathVariable Long roomId) {
        Room r = roomRepo.findById(roomId)
                         .orElseThrow(() -> new UserNotFoundException("Couldn't find a room with ID " + roomId));
        User u = userRepo.findById(userId)
                         .orElseThrow(() -> new UserNotFoundException("Couldn't find a user with ID " + userId));

        u.setRoom(r);
        System.out.println("Adding " + u + " to room " + r);

        return userRepo.save(u);
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User u, @PathVariable Long id) {
        return userRepo.findById(id)
                .map(usr -> {
                    usr.setUsername(u.getUsername());
                    usr.setRole(u.getRole());

                    return userRepo.save(usr);
                })
                .orElseGet(() -> userRepo.save(u));
    }
    
    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/query/50")
    List<User> getUsersWithIdHigherThan50() {
        return userRepo.getUsersWithIdHigherThan50();
    }
}
