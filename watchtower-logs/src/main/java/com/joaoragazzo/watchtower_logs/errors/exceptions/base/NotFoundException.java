package com.joaoragazzo.watchtower_logs.errors.exceptions.base;


public abstract class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
