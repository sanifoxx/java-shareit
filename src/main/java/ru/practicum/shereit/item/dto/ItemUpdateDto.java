package ru.practicum.shereit.item.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemUpdateDto {

    private Long id;

    @NotBlank(message = "The 'name' field cannot be empty")
    @Max(value = 255)
    private String name;

    @NotBlank(message = "The 'description' field cannot be empty")
    @Max(value = 512)
    private String description;

    private Boolean available;
}
