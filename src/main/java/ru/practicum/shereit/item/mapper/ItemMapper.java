package ru.practicum.shereit.item.mapper;

import ru.practicum.shereit.item.dto.ItemCreateDto;
import ru.practicum.shereit.item.dto.ItemDto;
import ru.practicum.shereit.item.dto.ItemUpdateDto;
import ru.practicum.shereit.item.model.Item;

public class ItemMapper {

    public static ItemDto toDto(Item item) {
        return ItemDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .build();
    }

    public static ItemCreateDto toCreateDto(Item item) {
        return ItemCreateDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .build();
    }

    public static ItemUpdateDto toUpdateDto(Item item) {
        return ItemUpdateDto.builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .available(item.getAvailable())
                .build();
    }

    public static Item toItem(ItemDto itemDto) {
        return Item.builder()
                .id(itemDto.getId())
                .name(itemDto.getName())
                .description(itemDto.getDescription())
                .available(itemDto.getAvailable())
                .build();
    }

    public static Item toItem(ItemCreateDto itemCreateDto) {
        return Item.builder()
                .id(itemCreateDto.getId())
                .name(itemCreateDto.getName())
                .description(itemCreateDto.getDescription())
                .available(itemCreateDto.getAvailable())
                .build();
    }

    public static Item toItem(ItemUpdateDto itemUpdateDto) {
        return Item.builder()
                .id(itemUpdateDto.getId())
                .name(itemUpdateDto.getName())
                .description(itemUpdateDto.getDescription())
                .available(itemUpdateDto.getAvailable())
                .build();
    }
}
