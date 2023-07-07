package ru.practicum.shereit.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ItemDto {

    private Long id;

    @NotNull(message = "The 'name' field must be present")
    @NotBlank(message = "The 'name' field cannot be empty")
    private String name;

    @NotNull(message = "The 'description' field must be present")
    @NotBlank(message = "The 'description' field cannot be empty")
    private String description;

    @NotNull(message = "The 'available' field must be present")
    @JsonProperty("available")
    private Boolean isAvailable;
}
