package ru.practicum.shereit.booking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shereit.booking.controller.BookingOwnerQueryState;
import ru.practicum.shereit.booking.controller.BookingQueryState;
import ru.practicum.shereit.booking.dao.BookingRepository;
import ru.practicum.shereit.booking.dto.BookingCreateDto;
import ru.practicum.shereit.booking.dto.BookingDto;
import ru.practicum.shereit.booking.dto.BookingMapper;
import ru.practicum.shereit.booking.dto.BookingUpdateDto;
import ru.practicum.shereit.booking.model.Booking;
import ru.practicum.shereit.booking.model.BookingStatus;
import ru.practicum.shereit.booking.service.BookingService;
import ru.practicum.shereit.error_handler.model.exception.access_is_denied.BookingAccessIsDeniedException;
import ru.practicum.shereit.error_handler.model.exception.not_found.ItemNotFoundException;
import ru.practicum.shereit.error_handler.model.exception.unavailable.BookingUnavailableException;
import ru.practicum.shereit.error_handler.model.exception.unavailable.ItemUnavailableException;
import ru.practicum.shereit.error_handler.model.exception.not_found.NotFoundException;
import ru.practicum.shereit.item.dao.ItemRepository;
import ru.practicum.shereit.item.model.Item;
import ru.practicum.shereit.user.dao.UserRepository;
import ru.practicum.shereit.user.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, UserRepository userRepository,
                              ItemRepository itemRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
    }

    @Transactional
    @Override
    public BookingDto createNewBooking(BookingCreateDto bookingCreateDto) {
        User booker = userRepository.findByIdOrElseThrow(bookingCreateDto.getBookerId());
        Item item = itemRepository.findByIdOrElseThrow(bookingCreateDto.getItemId());
        if (item.getOwner().getId().equals(booker.getId())) {
            throw new ItemNotFoundException("Потому что в тестах нужна 404 в таком случае");
        }
        if (!item.getAvailable()) {
            throw new ItemUnavailableException(
                    String.format("The item with id '%d' is not available", item.getId())
            );
        }
        Booking booking = BookingMapper.toBooking(bookingCreateDto);
        booking.setBooker(booker);
        booking.setItem(item);
        booking.setStatus(BookingStatus.WAITING);
        return BookingMapper.toDto(bookingRepository.save(booking));
    }

    @Transactional(readOnly = true)
    @Override
    public BookingDto getBookingById(Long bookingId, Long userId) {
        Booking booking = bookingRepository.findByIdOrElseThrow(bookingId);
        if (!userId.equals(booking.getBooker().getId()) && !userId.equals(booking.getItem().getOwner().getId())) {
            throw new NotFoundException("Because that in tests");
//            throw new AccessIsDeniedException(
//                    String.format("The user with id '%d' is not the owner of the booking with id '%d'",
//                            userId,
//                            booking.getId()
//                    )
//            );
        }
        return BookingMapper.toDto(booking);
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookingDto> getAllBookingsForCurrentUser(Long userId, BookingQueryState state) {
        userRepository.findByIdOrElseThrow(userId);
        return bookingRepository.findByBookerIdAndBookerState(userId, state.name(), LocalDateTime.now())
                .stream()
                .map(BookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public List<BookingDto> getAllBookingsForOwnerUser(Long userId, BookingOwnerQueryState state) {
        userRepository.findByIdOrElseThrow(userId);
        return bookingRepository.findByOwnerIdAndBookerState(userId, state.name(), LocalDateTime.now())
                .stream()
                .map(BookingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BookingDto updateBookingStatus(BookingUpdateDto bookingUpdateDto) {
        User owner = userRepository.findByIdOrElseThrow(bookingUpdateDto.getOwnerId());
        Booking booking = bookingRepository.findByIdOrElseThrow(bookingUpdateDto.getId());
        // TODO рефакторинг всего этого говна
        if (!booking.getStatus().equals(BookingStatus.WAITING)) {
            throw new BookingUnavailableException("Нельзя менять после принятия или отклонения");
        }
        if (!owner.getId().equals(booking.getItem().getOwner().getId())) {
            if (owner.getId().equals(booking.getBooker().getId())) {
                throw new NotFoundException("Because that in tests");
            }
            throw new BookingAccessIsDeniedException(
                    String.format("The user with id '%d' is not the owner of the booking with id '%d'",
                            bookingUpdateDto.getOwnerId(),
                            bookingUpdateDto.getId()
                    )
            );
        }
        booking.setStatus(
                bookingUpdateDto.getApproved()
                        ? BookingStatus.APPROVED
                        : BookingStatus.REJECTED
        );
        return BookingMapper.toDto(bookingRepository.save(booking));
    }
}
