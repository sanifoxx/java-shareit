package ru.practicum.shereit.user.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;

    @NotBlank(message = "The 'name' field cannot be empty")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "The 'name' field cannot be empty")
    @Size(max = 512)
    private String email;
}
