package ru.practicum.shereit.item.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemCreateDto {

    private Long id;

    @NotNull(message = "The 'name' field must be present")
    @NotBlank(message = "The 'name' field cannot be empty")
    @Max(value = 255)
    private String name;

    @NotNull(message = "The 'description' field must be present")
    @NotBlank(message = "The 'description' field cannot be empty")
    @Max(value = 512)
    private String description;

    @NotNull(message = "The 'available' field must be present")
    private Boolean available;
}
