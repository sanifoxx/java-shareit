package ru.practicum.shereit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserCreateDto {

    private Long id;

    @NotNull
    @NotBlank(message = "The 'name' field cannot be empty")
    @Max(value = 255)
    private String name;

    @NotNull
    @NotBlank(message = "The 'email' field cannot be empty")
    @Max(value = 512)
    @Email(message = "The 'email'='${validatedValue}' field must match the E-mail format")
    private String email;
}
