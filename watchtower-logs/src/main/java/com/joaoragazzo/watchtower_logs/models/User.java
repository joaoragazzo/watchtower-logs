package com.joaoragazzo.watchtower_logs.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotBlank(message = "invalidUsername")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "blankFirstName")
    private String firstName;

    @NotBlank(message = "blankLastName")
    private String lastName;

    @NotNull(message = "blankBirthDate")
    private LocalDate birthDate;

    @NotBlank(message = "blankPassword")
    private String password;

    @NotBlank(message = "blankEmail")
    @Email(message = "invalidEmail")
    @Column(unique = true)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

}
