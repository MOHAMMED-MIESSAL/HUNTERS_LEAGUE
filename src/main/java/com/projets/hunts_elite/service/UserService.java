package com.projets.hunts_elite.service;


import com.projets.hunts_elite.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    Optional<User> getUserById(UUID id);
    Optional<User> getUserByEmail(String email);
    Page<User> getAll(Pageable pageable);
    User create(User user);
    User update(UUID id, User user);
    void delete(UUID id);
    Optional<User> login(String email, String password);
}
