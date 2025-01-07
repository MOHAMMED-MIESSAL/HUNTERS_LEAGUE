package com.projets.hunts_elite.service.Impl;


import com.projets.hunts_elite.domain.User;
import com.projets.hunts_elite.exception.CustomValidationException;
import com.projets.hunts_elite.repository.UserRepository;
import com.projets.hunts_elite.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User update(UUID id, User user) {
        Optional<User> userToUpdate = userRepository.findById(id);
        if (userToUpdate.isEmpty()) {
            throw new CustomValidationException("User not found");
        }
        userToUpdate.get().setId(id);
        userToUpdate.get().setUsername(user.getUsername());
        userToUpdate.get().setPassword(passwordEncoder.encode(user.getPassword()));
        userToUpdate.get().setRole(user.getRole());
        userToUpdate.get().setFirstName(user.getFirstName());
        userToUpdate.get().setLastName(user.getLastName());
        userToUpdate.get().setCin(user.getCin());
        userToUpdate.get().setEmail(user.getEmail());
        userToUpdate.get().setNationality(user.getNationality());
        userToUpdate.get().setJoinDate(user.getJoinDate());
        userToUpdate.get().setLicenseExpirationDate(user.getLicenseExpirationDate());
        return userRepository.save(userToUpdate.get());
    }

    @Override
    public Optional<User> getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new CustomValidationException("User not found");
        }
        return user;
    }

    @Override
    public void delete(UUID id) {
        Optional<User> userToDelete = userRepository.findById(id);
        if (userToDelete.isEmpty()) {
            throw new CustomValidationException("User not found");
        }
        userRepository.delete(userToDelete.get());
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> login(String email, String password) {
        if (password == null) {
            throw new CustomValidationException("Password must not be null");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            User user = userRepository.findByEmail(email).get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return Optional.of(user);
            }
        }
       throw new CustomValidationException("Invalid credentials");
    }

}
