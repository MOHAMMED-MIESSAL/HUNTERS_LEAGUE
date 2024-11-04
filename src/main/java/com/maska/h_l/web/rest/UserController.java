package com.maska.h_l.web.rest;


import com.maska.h_l.domain.User;
import com.maska.h_l.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return userService.getAllUsers(page, size);
    }

    @GetMapping("/username")
    public ResponseEntity<User> getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/id")
    public ResponseEntity<User> getUserById(@RequestParam UUID id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<User>  createUser(@RequestBody User user) {
        User newUser = userService.saveUser(user);
        return ResponseEntity.ok(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestParam UUID id, @RequestBody User user) {
        user.setId(id);
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<User> findUserByNameOrEmail(@RequestParam String input) {
        return userService.findUserByNameOrEmail(input)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
    *    Implémenter les méthodes suivantes pour gérer les opérations CRUD sur la table User
    *  en utilisant la classe UserMapperUtil pour convertir les entités User en DTO UserDTO et vice versa.
     */


//    @GetMapping
//    public ResponseEntity<Page<UserDTO>> getAllUsers(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        Page<UserDTO> usersPage = userService.getAllUsers(page, size);
//        return ResponseEntity.ok(usersPage);
//    }
//
//
//    @GetMapping("/{id}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
//        Optional<UserDTO> userDTO = userService.getUserById(id);
//        return userDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//
//    @GetMapping("/search")
//    public ResponseEntity<UserDTO> findUserByNameOrEmail(@RequestParam String input) {
//        Optional<UserDTO> userDTO = userService.findUserByNameOrEmail(input);
//        return userDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//
//    @PostMapping()
//    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
//        UserDTO userDTO = userService.createUser(user);
//        return ResponseEntity.ok(userDTO);
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build(); // Renvoie un code HTTP 204 No Content
//    }
}
