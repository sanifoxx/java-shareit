package ru.practicum.shereit.item.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.practicum.shereit.error_handler.model.exception.access_is_denied.AccessIsDeniedException;
import ru.practicum.shereit.item.model.Item;
import ru.practicum.shereit.item.repository.ItemRepository;
import ru.practicum.shereit.item.service.ItemService;
import ru.practicum.shereit.user.model.User;
import ru.practicum.shereit.user.service.UserService;

import java.util.List;

@Service
@Qualifier("ItemServiceImpl")
public class ItemServiceImpl implements ItemService {

    @Qualifier("UserServiceImpl")
    private final UserService userService;

    @Qualifier("InMemoryItemRepository")
    private final ItemRepository repository;

    @Autowired
    public ItemServiceImpl(UserService userService, ItemRepository repository) {
        this.userService = userService;
        this.repository = repository;
    }

    @Override
    public Item createNewItem(Item item, Long userId) {
        User user = userService.getUserById(userId);
        item.setOwner(user);
        return repository.createNewItem(item);
    }

    @Override
    public Item getItemById(Long itemId) {
        return repository.getItemById(itemId);
    }

    @Override
    public List<Item> getItemsByUserId(Long userId) {
        userService.getUserById(userId);
        return repository.getItemsByUserId(userId);
    }

    @Override
    public List<Item> getAvailableItemsByQuery(String query) {
        return repository.getAvailableItemsByQuery(query.toLowerCase());
    }

    @Override
    public Item updateItem(Item item, Long userId) {
        item.setOwner(userService.getUserById(userId));
        if (!item.equals(getItemById(item.getId()))) {
            throw new AccessIsDeniedException(
                    String.format("Access denied: user ID '%d' cannot update item ID '%d' owned by user ID '%d'",
                            item.getOwner().getId(),
                            item.getId(),
                            getItemById(item.getId()).getOwner().getId()
                    )
            );
        }
        return repository.updateItem(item);
    }
}
