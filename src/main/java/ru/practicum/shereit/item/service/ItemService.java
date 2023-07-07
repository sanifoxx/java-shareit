package ru.practicum.shereit.item.service;

import ru.practicum.shereit.item.model.Item;

import java.util.List;

public interface ItemService {

    Item createNewItem(Item item, Long userId);

    Item getItemById(Long id);

    List<Item> getItemsByUserId(Long userId);

    List<Item> getAvailableItemsByQuery(String query);

    Item updateItem(Item item, Long userId);
}
