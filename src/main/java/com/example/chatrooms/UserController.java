package com.example.chatrooms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserRepository repo;

    @GetMapping("/users")
    List<User> getUsers() {
        return (List<User>) repo.findAll();
    }

    @GetMapping("/users/{id}")
    User getUser(@PathVariable Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new UserNotFoundException("Couldn't find a user with ID " + id));
    }

    @PostMapping("/users")
    User createUser(@RequestBody User u) {
        return repo.save(u);
    }

    @PutMapping("/users/{id}")
    User updateUser(@RequestBody User u, @PathVariable Long id) {
        return repo.findById(id)
                .map(usr -> {
                    usr.setUsername(u.getUsername());
                    usr.setRole(u.getRole());

                    return repo.save(usr);
                })
                .orElseGet(() -> {
                    return repo.save(u);
                });
    }

    @DeleteMapping("/users/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id) {
        repo.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/users/query/50")
    List<User> getUsersWithIdHigherThan50() {
        return repo.getUsersWithIdHigherThan50();
    }
}
