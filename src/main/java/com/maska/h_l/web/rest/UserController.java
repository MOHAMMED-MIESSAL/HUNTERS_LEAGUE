package com.maska.h_l.web.rest;


import com.maska.h_l.domain.User;
import com.maska.h_l.service.UserService;
import com.maska.h_l.service.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<UserDTO>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<UserDTO> usersPage = userService.getAllUsers(page, size);
        return ResponseEntity.ok(usersPage);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        Optional<UserDTO> userDTO = userService.getUserById(id);
        return userDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/search")
    public ResponseEntity<UserDTO> findUserByNameOrEmail(@RequestParam String input) {
        Optional<UserDTO> userDTO = userService.findUserByNameOrEmail(input);
        return userDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping()
    public ResponseEntity<UserDTO> createUser(@RequestBody User user) {
        UserDTO userDTO = userService.createUser(user);
        return ResponseEntity.ok(userDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // Renvoie un code HTTP 204 No Content
    }
}
