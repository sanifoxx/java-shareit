package ru.practicum.shereit.item.dto;

import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ItemUpdateDto {

    private Long id;

    @Size(max = 255)
    private String name;

    @Size(max = 512)
    private String description;

    private Boolean available;
}
