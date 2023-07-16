package ru.practicum.shereit.item.controller;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shereit.item.dto.ItemCreateDto;
import ru.practicum.shereit.item.dto.ItemDto;
import ru.practicum.shereit.item.dto.ItemUpdateDto;
import ru.practicum.shereit.item.service.ItemService;

import java.util.List;

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
    public ItemCreateDto createItem(@RequestBody @Valid ItemCreateDto itemCreateDto,
                                    @RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("POST /items | userId={}, ItemCreateDto-object: {}", userId, itemCreateDto);
        return itemService.createNewItem(itemCreateDto, userId);
    }

    @GetMapping("/{itemId}")
    public ItemDto getItem(@PathVariable Long itemId,
                           @RequestHeader(value = "X-Sharer-User-Id", required = false) Long userId) {
        log.info("GET /items/{} | userId={}", itemId, userId == null ? "Not Authorized" : userId);
        return itemService.getItemById(itemId, userId);
    }

    @GetMapping
    public List<ItemDto> getItemsForUser(@RequestHeader("X-Sharer-User-Id") Long userId) {
        log.info("GET /items | userId={}", userId);
        return itemService.getItemsByUserId(userId);
    }

    @GetMapping("/search")
    public List<ItemDto> getItemsBySearch(@RequestParam("text") String query) {
        log.info("GET /items/search?text={}", query);
        return itemService.getAvailableItemsByQuery(query);
    }

    @PatchMapping("/{itemId}")
    public ItemUpdateDto updateItem(@RequestBody ItemUpdateDto itemUpdateDto,
                              @RequestHeader("X-Sharer-User-Id") Long userId,
                              @PathVariable Long itemId) {
        log.info("PATCH /items/{} | userId={}, ItemUpdateDto-object: {}", itemId, userId, itemUpdateDto);
        itemUpdateDto.setId(itemId);
        return itemService.updateItem(itemUpdateDto, userId);
    }
}
