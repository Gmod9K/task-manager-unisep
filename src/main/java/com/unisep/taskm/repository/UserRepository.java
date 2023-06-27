package com.unisep.taskm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unisep.taskm.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNome(String nome);
}
