package com.joaoragazzo.watchtower_logs.security.handlers;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaoragazzo.watchtower_logs.errors.AuthenticationErrorCodes;
import com.joaoragazzo.watchtower_logs.web.dto.messages.ErrorMessageDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint {
    
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(new ErrorMessageDTO(AuthenticationErrorCodes.UNAUTHENTICATED_ACCESS)));
    }
}
