package com.maska.h_l.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.maska.h_l.domain.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String firstName;

    private String lastName;

    private String cin;

    private String email;

    private String nationality;

    private LocalDateTime joinDate;

    private LocalDateTime licenseExpirationDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Participation> participations;
}
