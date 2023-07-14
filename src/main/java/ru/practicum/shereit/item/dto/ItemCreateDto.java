package ru.practicum.shereit.item.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemCreateDto {

    private Long id;

    @NotBlank(message = "The 'name' field cannot be empty")
    @Size(max = 255)
    private String name;

    @NotBlank(message = "The 'description' field cannot be empty")
    @Size(max = 512)
    private String description;

    @NotNull(message = "The 'available' field must be present")
    private Boolean available;
}
