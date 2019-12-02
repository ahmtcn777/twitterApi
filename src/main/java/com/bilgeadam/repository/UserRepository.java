package com.bilgeadam.repository;

import com.bilgeadam.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    List<User> findUsersByUsernameContainingIgnoreCase(@Param("username") String username);
    List<User> findUsersByFirstnameContainingIgnoreCase(@Param("firstname") String firstname);
    List<User> findUsersByLastnameContainingIgnoreCase(@Param("lastname") String lastname);
    User findUserById(@Param("id") int id);
}
