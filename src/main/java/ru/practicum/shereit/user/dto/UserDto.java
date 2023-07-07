package ru.practicum.shereit.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    private Long id;

    @NotBlank(message = "The 'name' field cannot be empty")
    private String name;
}
