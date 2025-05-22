package com.joaoragazzo.watchtower_logs.errors.exceptions.auth;

import com.joaoragazzo.watchtower_logs.errors.exceptions.base.ConflictException;

public class EmailAlreadyTakenException extends ConflictException {
    public EmailAlreadyTakenException(String message) {
        super(message);
    }
}
