package com.joaoragazzo.watchtower_logs.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoragazzo.watchtower_logs.security.CustomUserDetails;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/")
public class Debug {
    
    @GetMapping
    @SecurityRequirement(name = "Cookie")
    public ResponseEntity<String> testing() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok("Hello, " + userDetails.getUser().getFirstName() + "! Your roles are " + userDetails.getAuthorities().toString());
    }

}
