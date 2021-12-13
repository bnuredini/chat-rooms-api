package com.example.chatrooms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE id > 50", nativeQuery = true)
    public List<User> getUsersWithIdHigherThan50();
}