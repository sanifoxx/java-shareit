package ru.practicum.shereit.user.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserCreateDto {

    private Long id;

    @NotNull
    @NotBlank(message = "The 'name' field cannot be empty")
    @Size(max = 255)
    private String name;

    @NotNull
    @NotBlank(message = "The 'email' field cannot be empty")
    @Size(max = 512)
    @Email(message = "The 'email'='${validatedValue}' field must match the E-mail format")
    private String email;
}
