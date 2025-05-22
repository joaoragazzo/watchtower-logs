package com.joaoragazzo.watchtower_logs.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.joaoragazzo.watchtower_logs.errors.AuthenticationErrorCodes;
import com.joaoragazzo.watchtower_logs.models.User;
import com.joaoragazzo.watchtower_logs.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(AuthenticationErrorCodes.USERNAME_NOT_FOUND));

        return new CustomUserDetails(user);
    }
}
