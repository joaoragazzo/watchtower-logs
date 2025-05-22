package com.joaoragazzo.watchtower_logs.web.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.joaoragazzo.watchtower_logs.errors.exceptions.base.ConflictException;
import com.joaoragazzo.watchtower_logs.web.dto.messages.ErrorMessageDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessageDTO> conflictHandler(ConflictException exception) {
        return ResponseEntity
                .status(409)
                .body(new ErrorMessageDTO(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageDTO> notValidContent(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        return ResponseEntity
                .status(400)
                .body(new ErrorMessageDTO(message));
    }

    @ExceptionHandler(UsernameNotFoundException.class) 
    public ResponseEntity<ErrorMessageDTO> usernameNotFound(UsernameNotFoundException exception) {
        return ResponseEntity
                .status(404)
                .body(new ErrorMessageDTO(exception.getMessage()));
    }
}
