package com.joaoragazzo.watchtower_logs.errors.exceptions.base;

public abstract class ConflictException extends RuntimeException {
    public ConflictException(String message) {
        super(message);
    }
}
