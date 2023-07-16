package ru.practicum.shereit.item.dao.impl;

import org.springframework.context.annotation.Lazy;
import ru.practicum.shereit.error_handler.model.exception.not_found.ItemNotFoundException;
import ru.practicum.shereit.item.dao.ItemRepository;
import ru.practicum.shereit.item.dao.ItemRepositoryCustom;
import ru.practicum.shereit.item.model.Item;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private final ItemRepository itemRepository;

    public ItemRepositoryCustomImpl(@Lazy ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item findByIdOrElseThrow(Long id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(
                String.format("Item with id '%d' not found", id)
        ));
    }
}
