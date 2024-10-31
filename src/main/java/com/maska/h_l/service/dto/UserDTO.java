package com.maska.h_l.service.dto;

import com.maska.h_l.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private UUID id;
    private String username;
    private Role role;
    private String firstName;
    private String lastName;
    private String cin;
    private String email;
    private String nationality;
}
