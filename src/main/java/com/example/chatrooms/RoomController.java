package com.example.chatrooms;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {

    @Autowired
    private RoomRepository repo;

    @GetMapping("/rooms")
    List<Room> getRooms() {
        return repo.findAll();
    }

    @GetMapping("/rooms/{id}")
    Room getRoom(@PathVariable Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new RoomNotFoundException(String.valueOf(id)));
    }

    @PostMapping("/rooms")
    Room createRoom(@RequestBody Room r) {
        return repo.save(r);
    }

    @DeleteMapping("/rooms/{id}")
    ResponseEntity<?> deleteRoom(@PathVariable Long id) {
        repo.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
