package com.maska.h_l.service.dto.mapper;

import com.maska.h_l.domain.User;
import com.maska.h_l.service.dto.UserDTO;

public class UserMapperUtil {

    public static UserDTO userToUserDTO(User user) {
        if (user == null) {
            return null;
        }
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getCin(),
                user.getEmail(),
                user.getNationality()
        );
    }

    public static User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(null)
                .role(userDTO.getRole())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .cin(userDTO.getCin())
                .email(userDTO.getEmail())
                .nationality(userDTO.getNationality())
                .joinDate(null)
                .licenseExpirationDate(null)
                .build();
    }
}
