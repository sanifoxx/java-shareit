package ru.practicum.shereit.item.repository;

import ru.practicum.shereit.item.model.Item;

import java.util.List;

public interface ItemRepository {

    Item createNewItem(Item item);

    Item getItemById(Long itemId);

    List<Item> getItemsByUserId(Long userId);

    List<Item> getAvailableItemsByQuery(String query);

    Item updateItem(Item item);
}
