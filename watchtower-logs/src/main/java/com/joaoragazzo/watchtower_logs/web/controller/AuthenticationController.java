package com.joaoragazzo.watchtower_logs.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaoragazzo.watchtower_logs.security.jwt.JwtService;
import com.joaoragazzo.watchtower_logs.service.UserService;
import com.joaoragazzo.watchtower_logs.web.dto.forms.auth.LoginUserDTO;
import com.joaoragazzo.watchtower_logs.web.dto.forms.auth.RegisterUserDTO;
import com.joaoragazzo.watchtower_logs.web.dto.messages.ErrorMessageDTO;
import com.joaoragazzo.watchtower_logs.web.dto.messages.SuccessMessageDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The user has been created"),
            @ApiResponse(responseCode = "409", description = "Email or username already taken", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid data", content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))
    })
    public ResponseEntity<SuccessMessageDTO> registerUser(@RequestBody @Valid RegisterUserDTO registerUserDTO) {
        return ResponseEntity.status(201).body(userService.registerNewUser(registerUserDTO));
    }

    @PostMapping("/login")
    @Operation(summary = "Login a existing user and set JWT token if success")
    public ResponseEntity<SuccessMessageDTO> login(@RequestBody @Valid LoginUserDTO loginUserDTO, HttpServletResponse response) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(loginUserDTO.username(), loginUserDTO.password());
        Authentication authenticated = authenticationManager.authenticate(authentication);
        String token = jwtService.generateToken((UserDetails) authenticated.getPrincipal());
        
        Cookie cookie = new Cookie("session", token);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);

        SuccessMessageDTO success = new SuccessMessageDTO(cookie.getValue());
        return ResponseEntity.ok(success);
    }

}
