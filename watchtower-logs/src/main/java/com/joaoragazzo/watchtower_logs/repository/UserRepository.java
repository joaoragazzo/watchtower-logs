package com.joaoragazzo.watchtower_logs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaoragazzo.watchtower_logs.models.User;
import java.util.Optional;



public interface UserRepository extends JpaRepository<User, Integer> {
    
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);
    
}
