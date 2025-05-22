package com.joaoragazzo.watchtower_logs.errors.exceptions.auth;

import com.joaoragazzo.watchtower_logs.errors.exceptions.base.UnauthorizedException;

public class InvalidJwtException extends UnauthorizedException {
    public InvalidJwtException(String message) { 
        super(message);
    }
}
