package com.joaoragazzo.watchtower_logs.security.handlers;

import java.io.IOException;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaoragazzo.watchtower_logs.errors.AuthenticationErrorCodes;
import com.joaoragazzo.watchtower_logs.web.dto.messages.ErrorMessageDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException, ServletException {

        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        response.setStatus(HttpServletResponse.SC_FORBIDDEN); 
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(new ErrorMessageDTO(AuthenticationErrorCodes.UNAUTHORIZED_ACCESS)));

    }
}