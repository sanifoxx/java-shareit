package ru.practicum.shereit.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Data
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "The 'name' field cannot be empty")
    @Max(value = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @NotBlank(message = "The 'email' field cannot be empty")
    @Max(value = 512)
    @Email(message = "The 'email'='${validatedValue}' field must match the E-mail format")
    @Column(name = "email", unique = true, nullable = false)
    private String email;
}
