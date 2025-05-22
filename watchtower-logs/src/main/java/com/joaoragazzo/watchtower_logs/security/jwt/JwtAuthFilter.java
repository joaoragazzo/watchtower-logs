package com.joaoragazzo.watchtower_logs.security.jwt;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.joaoragazzo.watchtower_logs.errors.AuthenticationErrorCodes;
import com.joaoragazzo.watchtower_logs.errors.exceptions.auth.UsernameNotFoundException;
import com.joaoragazzo.watchtower_logs.security.services.CustomUserDetailsService;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String jwt = getJwtFromCookies(request);
        
        if (Objects.isNull(jwt) || jwt.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String username = jwtService.extractUsername(jwt);

            if (!Objects.isNull(username) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        } catch (SignatureException | MalformedJwtException e) {
            handleAuthException(response, AuthenticationErrorCodes.INVALID_JWT_SIGNATURE);
            return;
        } catch (UsernameNotFoundException e) {
            handleAuthException(response, AuthenticationErrorCodes.USERNAME_NOT_FOUND);
            return;
        }
        

        filterChain.doFilter(request, response);
    
    }

    private void handleAuthException(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(
            objectMapper.writeValueAsString(Map.of(
                "error", message
            ))
        );
    }

    private String getJwtFromCookies(HttpServletRequest request) {
        if (!Objects.isNull(request.getCookies())) {
            for (Cookie cookie : request.getCookies()) {
                if ("session".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
