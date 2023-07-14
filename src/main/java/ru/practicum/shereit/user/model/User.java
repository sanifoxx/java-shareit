package ru.practicum.shereit.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "The 'name' field cannot be empty")
    @Size(max = 255)
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "The 'email' field cannot be empty")
    @Size(max = 512)
    @Email(message = "The 'email'='${validatedValue}' field must match the E-mail format")
    @Column(name = "email", unique = true, nullable = false)
    private String email;
}
