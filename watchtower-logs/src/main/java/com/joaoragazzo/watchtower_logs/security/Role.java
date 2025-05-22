package com.joaoragazzo.watchtower_logs.security;

public enum Role {
    USER,
    ADMIN;

    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
