package com.joaoragazzo.watchtower_logs.security.services;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.joaoragazzo.watchtower_logs.models.User;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream()
            .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override 
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
