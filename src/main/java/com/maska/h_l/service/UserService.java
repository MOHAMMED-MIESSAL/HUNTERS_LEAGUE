package com.maska.h_l.service;

import com.maska.h_l.domain.User;
import com.maska.h_l.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ParticipationService participationService;

    // Injection par constructeur de UserRepository et ParticipationService
    public UserService(UserRepository userRepository, ParticipationService participationService) {
        this.userRepository = userRepository;
        this.participationService = participationService;
    }


    /*
     *    Implémenter les méthodes suivantes pour gérer les opérations CRUD sur la table User
     */

    public Page<User> getAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAll(pageable);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getUserById(UUID userId) {
        return userRepository.findById(userId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(UUID userId) {
        // userRepository.deleteById(userId);

        // Suppression des participations associées à cet utilisateur
        participationService.deleteAllByUserId(userId);
        // Suppression de l'utilisateur
        userRepository.deleteById(userId);
    }

    public Optional<User> findUserByNameOrEmail(String input) {
        return userRepository.findByUsername(input)
                .or(() -> userRepository.findByEmail(input));
    }

    /*
    *    Implémenter les méthodes suivantes pour gérer les opérations CRUD sur la table User
    *   en utilisant la classe UserMapperUtil pour convertir les entités User en DTO UserDTO et vice versa.
     */

//    public Page<UserDTO> getAllUsers(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<User> usersPage = userRepository.findAll(pageable);
//        // Convert each User to UserDTO
//        return usersPage.map(UserMapperUtil::userToUserDTO);
//    }
//
//    public UserDTO createUser(User user) {
//        return UserMapperUtil.userToUserDTO(userRepository.save(user));
//    }
//
//    public Optional<UserDTO> getUserById(UUID id) {
//        return userRepository.findById(id).map(UserMapperUtil::userToUserDTO);
//    }
//
//    public User updateUser(UUID id, User user) {
//        return userRepository.save(user);
//    }
//
//    public void deleteUser(UUID id) {
//        userRepository.deleteById(id);
//    }
//
//    public Optional<UserDTO> findUserByNameOrEmail(String input) {
//        return userRepository.findByUsername(input)
//                .map(UserMapperUtil::userToUserDTO)
//                .or(() -> userRepository.findByEmail(input).map(UserMapperUtil::userToUserDTO));
//    }


    /*
    * 1. Créer un arbre binaire pour faire les operation CRUD sur la table User
     */

//    @PostConstruct
//    public void init() {
//        // Charger les utilisateurs existants dans l'arbre
//        userRepository.findAll().forEach(userTree::insert);
//    }
//
//    public UserDTO createUser(User user) {
//        User savedUser = userRepository.save(user);
//        userTree.insert(savedUser);
//        return UserMapperUtil.userToUserDTO(savedUser);
//    }
//
//    public Optional<UserDTO> getUserById(UUID id) {
//        User user = userTree.search(id);
//        return Optional.ofNullable(user).map(UserMapperUtil::userToUserDTO);
//    }
//
//    @Transactional
//    public void deleteUser(UUID id) {
//        userTree.delete(id);
//        userRepository.deleteById(id);
//    }
//
//    public Page<UserDTO> getAllUsers(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size);
//        Page<User> usersPage = userRepository.findAll(pageable);
//
//        // On suppose que vous chargez les utilisateurs dans l'arbre à ce moment-là.
//        usersPage.forEach(user -> userTree.insert(user)); // Mise à jour de l'arbre binaire
//        return usersPage.map(UserMapperUtil::userToUserDTO);
//    }
//
//    public Optional<UserDTO> findUserByNameOrEmail(String input) {
//        // Rechercher dans l'arbre binaire
//        User user = userTree.searchByNameOrEmail(input);
//        if (user != null) {
//            return Optional.of(UserMapperUtil.userToUserDTO(user));
//        }
//        return userRepository.findByUsername(input)
//                .map(UserMapperUtil::userToUserDTO)
//                .or(() -> userRepository.findByEmail(input).map(UserMapperUtil::userToUserDTO));
//    }
}
