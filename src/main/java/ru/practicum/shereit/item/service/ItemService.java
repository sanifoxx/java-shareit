package ru.practicum.shereit.item.service;

import ru.practicum.shereit.item.dto.ItemCreateDto;
import ru.practicum.shereit.item.dto.ItemDto;
import ru.practicum.shereit.item.dto.ItemUpdateDto;

import java.util.List;

public interface ItemService {

    ItemCreateDto createNewItem(ItemCreateDto item, Long userId);

    ItemDto getItemById(Long id, Long userId);

    List<ItemDto> getItemsByUserId(Long userId);

    List<ItemDto> getAvailableItemsByQuery(String query);

    ItemUpdateDto updateItem(ItemUpdateDto itemUpdateDto, Long userId);
}
