package com.example.finalproject.person.repository;

import com.example.finalproject.person.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
