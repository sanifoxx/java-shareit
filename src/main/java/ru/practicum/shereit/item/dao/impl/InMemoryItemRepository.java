package ru.practicum.shereit.item.dao.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.practicum.shereit.error_handler.model.exception.not_found.ItemNotFoundException;
import ru.practicum.shereit.item.model.Item;
import ru.practicum.shereit.item.dao.ItemRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Qualifier("InMemoryItemRepository")
public class InMemoryItemRepository implements ItemRepository {

    private final Map<Long, Item> items = new HashMap<>();

    @Override
    public Item createNewItem(Item item) {
        item.setId(getId());
        items.put(item.getId(), item);
        return item;
    }

    @Override
    public Item getItemById(Long itemId) {
        Item item = items.get(itemId);
        if (item == null) {
            throw new ItemNotFoundException(
                    String.format("Item with id '%d' not found", itemId)
            );
        }
        return item;
    }

    @Override
    public List<Item> getItemsByUserId(Long userId) {
        return items.values()
                .stream()
                .filter(x -> userId.equals(x.getOwner().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Item> getAvailableItemsByQuery(String query) {
        return items.values()
                .stream()
                .filter(x -> !query.isBlank() && x.getAvailable()
                        && (x.getName().toLowerCase().contains(query)
                        || x.getDescription().toLowerCase().contains(query))
                )
                .collect(Collectors.toList());
    }

    @Override
    public Item updateItem(Item item) {
        Item memoryItem = getItemById(item.getId());
        memoryItem.setName(
                item.getName() == null || item.getName().isBlank()
                        ? memoryItem.getName()
                        : item.getName()
        );
        memoryItem.setDescription(
                item.getDescription() == null
                        ? memoryItem.getDescription()
                        : item.getDescription()
        );
        memoryItem.setAvailable(
                item.getAvailable() == null
                        ? memoryItem.getAvailable()
                        : item.getAvailable()
        );
        return getItemById(item.getId());
    }

    private Long getId() {
        return 1 + items.values()
                .stream()
                .mapToLong(Item::getId)
                .max()
                .orElse(0);
    }
}
