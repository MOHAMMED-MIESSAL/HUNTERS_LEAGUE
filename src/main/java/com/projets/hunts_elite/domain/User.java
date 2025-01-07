package com.projets.hunts_elite.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.projets.hunts_elite.domain.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "user", indexes = {
        @Index(name = "idx_user_username", columnList = "username"),
        @Index(name = "idx_user_email", columnList = "email"),
        @Index(name = "idx_user_cin", columnList = "cin")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Username cannot be empty or null")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Password cannot be empty or null")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String password;

    @NotNull(message = "Role cannot be null")
    @Enumerated(EnumType.STRING)
    private Role role;

    @NotBlank(message = "First name cannot be empty or null")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty or null")
    private String lastName;

    @NotBlank(message = "CIN cannot be empty or null")
    @Size(min = 5, max = 20, message = "CIN must be between 5 and 20 characters")
    private String cin;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email cannot be empty or null")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Phone number cannot be empty or null")
    private String nationality;

    @PastOrPresent(message = "Join date must be in the past or present")
    @NotNull(message = "Join date cannot be null")
    private LocalDateTime joinDate;

    @FutureOrPresent(message = "License expiration date must be in the future or present")
    @NotNull(message = "License expiration date cannot be null")
    private LocalDateTime licenseExpirationDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Participation> participations;
}
