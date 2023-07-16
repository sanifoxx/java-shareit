package ru.practicum.shereit.item.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shereit.booking.dao.BookingRepository;
import ru.practicum.shereit.booking.model.Booking;
import ru.practicum.shereit.error_handler.model.exception.access_is_denied.AccessIsDeniedException;
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

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Qualifier("ItemServiceImpl")
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, UserRepository userRepository,
                           BookingRepository bookingRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    @Override
    public ItemCreateDto createNewItem(ItemCreateDto itemCreateDto, Long userId) {
        final User user = userRepository.findByIdOrElseThrow(userId);
        final Item item = ItemMapper.toItem(itemCreateDto);
        item.setOwner(user);
        return ItemMapper.toCreateDto(itemRepository.save(item));
    }

    @Transactional(readOnly = true)
    @Override
    public ItemDto getItemById(Long itemId, Long userId) {
        Item item = itemRepository.findByIdOrElseThrow(itemId);
        ItemDto itemDto = ItemMapper.toDto(item);

        if (userId.equals(item.getOwner().getId())) {
            List<Booking> bookings = bookingRepository.findAllByItemId(item.getId());
            LocalDateTime now = LocalDateTime.now();

            Booking lastBooking = bookings.stream()
                    .filter(b -> ((b.getEnd().isEqual(now) || b.getEnd().isBefore(now))
                            || (b.getStart().isEqual(now) || b.getStart().isBefore(now))))
                    .findFirst()
                    .orElse(null);

            Booking nextBooking = bookings.stream()
                    .filter(b -> b.getStart().isAfter(now))
                    .reduce((first, second) -> second)
                    .orElse(null);

            itemDto.setLastBooking(lastBooking == null ? null : new ItemDto.ItemBooking(
                    lastBooking.getId(),
                    lastBooking.getBooker().getId()));

            itemDto.setNextBooking(nextBooking == null ? null : new ItemDto.ItemBooking(
                    nextBooking.getId(),
                    nextBooking.getBooker().getId()));
        }

        return itemDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ItemDto> getItemsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(String.format("User with id '%d' not found", userId));
        }
        return itemRepository.getItemsByOwnerId(userId)
                .stream()
                .map(item -> {
                    ItemDto itemDto = ItemMapper.toDto(item);
                    List<Booking> bookings = bookingRepository.findAllByItemId(item.getId());
                    LocalDateTime now = LocalDateTime.now();

                    Booking lastBooking = bookings.stream()
                            .filter(b -> ((b.getEnd().isEqual(now) || b.getEnd().isBefore(now))
                                    || (b.getStart().isEqual(now) || b.getStart().isBefore(now))))
                            .findFirst()
                            .orElse(null);

                    Booking nextBooking = bookings.stream()
                            .filter(b -> b.getStart().isAfter(now))
                            .reduce((first, second) -> second)
                            .orElse(null);

                    itemDto.setLastBooking(lastBooking == null ? null : new ItemDto.ItemBooking(
                            lastBooking.getId(),
                            lastBooking.getBooker().getId()));

                    itemDto.setNextBooking(nextBooking == null ? null : new ItemDto.ItemBooking(
                            nextBooking.getId(),
                            nextBooking.getBooker().getId()));

                    return itemDto;
                })
                .sorted(Comparator.comparing(ItemDto::getId))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ItemDto> getAvailableItemsByQuery(String query) {
        if (query.isBlank()) {
            return Collections.emptyList();
        }
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
        Item memoryItem = itemRepository.findByIdOrElseThrow(itemUpdateDto.getId());
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
