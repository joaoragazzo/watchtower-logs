package com.joaoragazzo.watchtower_logs.errors.exceptions.auth;

import com.joaoragazzo.watchtower_logs.errors.exceptions.base.ConflictException;

public class UsernameAlreadyTakenException extends ConflictException {
    public UsernameAlreadyTakenException(String message) {
        super(message);
    }
}
