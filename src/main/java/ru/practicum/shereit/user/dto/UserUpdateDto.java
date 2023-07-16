package ru.practicum.shereit.user.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateDto {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 512)
    @Email
    private String email;
}
