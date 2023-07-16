package ru.practicum.shereit.item.dao;

import ru.practicum.shereit.item.model.Item;

public interface ItemRepositoryCustom {

    Item findByIdOrElseThrow(Long id);
}
