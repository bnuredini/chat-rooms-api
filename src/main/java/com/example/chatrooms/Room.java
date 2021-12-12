package com.example.chatrooms;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "room")
    private List<User> users;

    public Room() {}

    public Room(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User u) {
        users.add(u);

        if (u.getRoom() != this) u.setRoom(this);
    }

    @Override
    public String toString() {
        return "Room {" +
                   "id=" + this.id + ", " +
                   "name='" + this.name + '\'' +  ", " +
               '}';
    }
}
