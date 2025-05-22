package com.joaoragazzo.watchtower_logs.errors.exceptions.base;

public abstract class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) { 
        super(message);
    }
}
