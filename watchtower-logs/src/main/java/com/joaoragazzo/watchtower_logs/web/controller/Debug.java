package com.joaoragazzo.watchtower_logs.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Debug {
    
    @GetMapping
    public ResponseEntity<String> testing() {
        return ResponseEntity.ok().body("Hello world!");
    }

}
