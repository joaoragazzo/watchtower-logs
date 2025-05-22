package com.joaoragazzo.watchtower_logs.service;

import org.springframework.stereotype.Service;

import com.joaoragazzo.watchtower_logs.errors.exceptions.auth.EmailAlreadyTakenException;
import com.joaoragazzo.watchtower_logs.errors.exceptions.auth.UsernameAlreadyTakenException;
import com.joaoragazzo.watchtower_logs.repository.UserRepository;
import com.joaoragazzo.watchtower_logs.web.dto.forms.auth.RegisterUserDTO;
import com.joaoragazzo.watchtower_logs.web.dto.messages.SuccessMessageDTO;
import com.joaoragazzo.watchtower_logs.web.mapper.UserMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public SuccessMessageDTO registerNewUser(RegisterUserDTO registerUserDTO) {
        validateUniqueFields(registerUserDTO);
        userRepository.save(userMapper.toUser(registerUserDTO));
        return new SuccessMessageDTO("success_user_creation");
    }

    private void validateUniqueFields(RegisterUserDTO registerUserDTO) {
        if (userRepository.existsByUsername(registerUserDTO.username()))
            throw new UsernameAlreadyTakenException("username_already_taken");

        if (userRepository.existsByEmail(registerUserDTO.email()))
            throw new EmailAlreadyTakenException("email_already_taken");
    }
}
