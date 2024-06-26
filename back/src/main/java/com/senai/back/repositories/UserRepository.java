package com.senai.back.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.senai.back.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
