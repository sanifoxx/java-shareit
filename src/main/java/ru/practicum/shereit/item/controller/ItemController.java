package ru.practicum.shereit.item.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shereit.item.dto.ItemDto;
import ru.practicum.shereit.item.mapper.ItemMapper;
import ru.practicum.shereit.item.model.Item;
import ru.practicum.shereit.item.service.ItemService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
@Slf4j
public class ItemController {

    @Qualifier("ItemServiceImpl")
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDto createItem(@RequestBody @Valid ItemDto itemDto,
                              @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("POST /items | userId={}, ItemDto-object: {}", userId, itemDto);
        Item item = ItemMapper.toItem(itemDto);
        return ItemMapper.toDto(itemService.createNewItem(item, userId));
    }

    @GetMapping("/{itemId}")
    public ItemDto getItem(@PathVariable Long itemId,
                           @RequestHeader(value = "X-Sharer-User-Id", required = false) Long userId) {
        log.info("GET /items/{} | userId={}", itemId, userId == null ? "Not Authorized" : userId);
        return ItemMapper.toDto(itemService.getItemById(itemId));
    }

    @GetMapping
    public List<ItemDto> getItemsForUser(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("GET /items | userId={}", userId);
        return itemService.getItemsByUserId(userId)
                .stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<ItemDto> getItemsBySearch(@RequestParam("text") String query) {
        log.info("GET /items/search?text={}", query);
        return itemService.getAvailableItemsByQuery(query)
                .stream()
                .map(ItemMapper::toDto)
                .collect(Collectors.toList());
    }

    @PatchMapping("/{itemId}")
    public ItemDto updateItem(@RequestBody ItemDto itemDto,
                              @RequestHeader("X-Sharer-User-Id") Long userId,
                              @PathVariable Long itemId) {
        log.info("PATCH /items/{} | userId={}, ItemDto-object: {}", itemId, userId, itemDto);
        itemDto.setId(itemId);
        Item item = ItemMapper.toItem(itemDto);
        return ItemMapper.toDto(itemService.updateItem(item, userId));
    }
}
