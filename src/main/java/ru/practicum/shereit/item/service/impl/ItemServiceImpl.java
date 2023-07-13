package ru.practicum.shereit.item.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shereit.error_handler.model.exception.access_is_denied.AccessIsDeniedException;
import ru.practicum.shereit.error_handler.model.exception.not_found.ItemNotFoundException;
import ru.practicum.shereit.error_handler.model.exception.not_found.UserNotFoundException;
import ru.practicum.shereit.item.dto.ItemCreateDto;
import ru.practicum.shereit.item.dto.ItemDto;
import ru.practicum.shereit.item.dto.ItemUpdateDto;
import ru.practicum.shereit.item.mapper.ItemMapper;
import ru.practicum.shereit.item.model.Item;
import ru.practicum.shereit.item.dao.ItemRepository;
import ru.practicum.shereit.item.service.ItemService;
import ru.practicum.shereit.user.dao.UserRepository;
import ru.practicum.shereit.user.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public ItemCreateDto createNewItem(ItemCreateDto itemCreateDto, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(
                        String.format("User with id '%d' not found", userId)
        ));
        Item item = ItemMapper.toItem(itemCreateDto);
        item.setOwner(user);
        return ItemMapper.toCreateDto(itemRepository.save(item));
    }

    @Transactional(readOnly = true)
    @Override
    public ItemDto getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new ItemNotFoundException(
                String.format("Item with id '%d' not found", itemId)
        ));
        return ItemMapper.toDto(item);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ItemDto> getItemsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(String.format("User with id '%d' not found", userId));
        }
        return itemRepository.getItemsByOwnerId(userId)
                .stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ItemDto> getAvailableItemsByQuery(String query) {
        return itemRepository.getAvailableItemsByQuery(query)
                .stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ItemUpdateDto updateItem(ItemUpdateDto itemUpdateDto, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(String.format("User with id '%d' not found", userId));
        }
        Item memoryItem = itemRepository.findById(itemUpdateDto.getId()).orElseThrow(() -> new ItemNotFoundException(
                String.format("Item with id '%d' not found", itemUpdateDto.getId())
        ));
        if (!userId.equals(memoryItem.getOwner().getId())) {
            throw new AccessIsDeniedException(
                    String.format("Access denied: user ID '%d' cannot update item ID '%d' owned by user ID '%d'",
                            userId,
                            itemUpdateDto.getId(),
                            memoryItem.getOwner().getId()
                    )
            );
        }
        if (itemUpdateDto.getName() != null) {
            memoryItem.setName(itemUpdateDto.getName());
        }
        if (itemUpdateDto.getDescription() != null) {
            memoryItem.setDescription(itemUpdateDto.getDescription());
        }
        if (itemUpdateDto.getAvailable() != null) {
            memoryItem.setAvailable(itemUpdateDto.getAvailable());
        }
        return ItemMapper.toUpdateDto(itemRepository.save(memoryItem));
    }
}
