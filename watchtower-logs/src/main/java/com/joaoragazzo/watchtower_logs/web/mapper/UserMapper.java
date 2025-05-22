package com.joaoragazzo.watchtower_logs.web.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.joaoragazzo.watchtower_logs.models.User;
import com.joaoragazzo.watchtower_logs.web.dto.forms.auth.RegisterUserDTO;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class UserMapper {
    
    private final PasswordEncoder passwordEncoder;


    public User toUser(RegisterUserDTO registerUserDTO) {
        User newUser = User.builder()
            .username(registerUserDTO.username())
            .firstName(registerUserDTO.name())
            .lastName(registerUserDTO.surname())
            .email(registerUserDTO.email())
            .password(passwordEncoder.encode(registerUserDTO.password()))
            .birthDate(registerUserDTO.birthDate())
            .build();
        return newUser;
    }
}
