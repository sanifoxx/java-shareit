package ru.practicum.shereit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDto {

    private Long id;

    @NotBlank
    @Max(value = 255)
    private String name;

    @NotBlank
    @Max(value = 512)
    @Email
    private String email;
}
