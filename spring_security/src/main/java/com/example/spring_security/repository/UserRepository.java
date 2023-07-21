package com.example.spring_security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring_security.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

Optional<User>	findByEmail(String email);
}
