package com.projets.hunts_elite.repository;



import com.projets.hunts_elite.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Page<User> findAll(Pageable pageable);
    Optional<User> findByEmail(String email);
}
