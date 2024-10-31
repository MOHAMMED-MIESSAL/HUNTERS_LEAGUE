package com.maska.h_l.service;

import com.maska.h_l.domain.User;
import com.maska.h_l.repository.UserRepository;
import com.maska.h_l.service.dto.UserDTO;
import com.maska.h_l.service.dto.mapper.UserMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User user) {
        return userRepository.save(user);
    }


    public Optional<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(UserMapperUtil::userToUserDTO)
                .collect(Collectors.toList());
    }


    public User updateUser(UUID id, User user) {
        return userRepository.save(user);
    }


    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
