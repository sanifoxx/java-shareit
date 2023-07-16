package ru.practicum.shereit.user.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
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
