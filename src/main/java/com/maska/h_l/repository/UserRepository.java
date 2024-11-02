package com.maska.h_l.repository;


import com.maska.h_l.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Page<User> findAll(Pageable pageable);

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
