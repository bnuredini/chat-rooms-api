package com.example.chatrooms;

//import com.example.chatrooms.User;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//public interface UserRepository extends JpaRepository<User, Long> {
//}
//
//

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM user WHERE id > 50", nativeQuery = true)
    public List<User> getUsersWithIdHigherThan50();
}