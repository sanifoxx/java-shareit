package ru.practicum.shereit.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemDto {

    private Long id;

    @NotBlank(message = "The 'name' field cannot be empty")
    private String name;

    @NotBlank(message = "The 'description' field cannot be empty")
    private String description;

    @NotNull(message = "The 'available' field must be present")
    private Boolean available;
}
