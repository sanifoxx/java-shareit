package ru.practicum.shereit.item.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
