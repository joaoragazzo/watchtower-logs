package com.joaoragazzo.watchtower_logs.errors.exceptions.auth;

import com.joaoragazzo.watchtower_logs.errors.exceptions.base.NotFoundException;

public class UsernameNotFoundException extends NotFoundException {
    public UsernameNotFoundException(String message) {
        super(message);
    }
}
