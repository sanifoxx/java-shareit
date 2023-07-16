package ru.practicum.shereit.booking.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shereit.booking.dto.BookingCreateDto;
import ru.practicum.shereit.booking.dto.BookingDto;
import ru.practicum.shereit.booking.dto.BookingUpdateDto;
import ru.practicum.shereit.booking.service.BookingService;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@Slf4j
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingDto createBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                    @Valid @RequestBody BookingCreateDto bookingCreateDto) {
        log.info("POST /bookings | userId={}, bookingCreateDto-Object: {}", userId, bookingCreateDto);
        bookingCreateDto.setBookerId(userId);
        return bookingService.createNewBooking(bookingCreateDto);
    }

    @GetMapping("/{bookingId}")
    public BookingDto getBookingById(@RequestHeader("X-Sharer-User-Id") Long userId,
                                     @PathVariable("bookingId") Long bookingId) {
        log.info("GET /bookings/{} | userId={}", bookingId, userId);
        return bookingService.getBookingById(bookingId, userId);
    }

    @GetMapping()
    public List<BookingDto> getAllBookingsForCurrentUser(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestParam(value = "state", defaultValue = "ALL") BookingQueryState state) {
        log.info("GET /bookings?state={} | userId={}", state, userId);
        return bookingService.getAllBookingsForCurrentUser(userId, state);
    }

    @GetMapping("/owner")
    public List<BookingDto> getAllBookingsForOwnerUser(
            @RequestHeader("X-Sharer-User-Id") Long userId,
            @RequestParam(value = "state", defaultValue = "ALL") BookingOwnerQueryState state) {
        log.info("GET /bookings/owner?state={} | userId={}", state, userId);
        return bookingService.getAllBookingsForOwnerUser(userId, state);
    }

    @PatchMapping("/{bookingId}")
    public BookingDto updateBookingStatus(@RequestHeader("X-Sharer-User-Id") Long userId,
                                          @RequestParam("approved") Boolean approved,
                                          @PathVariable("bookingId") Long bookingId) {
        log.info("PATCH /bookings/{}?approved={} | userId={}", bookingId, approved,userId);
        BookingUpdateDto bookingUpdateDto = BookingUpdateDto.builder()
                .id(bookingId)
                .ownerId(userId)
                .approved(approved)
                .build();
        return bookingService.updateBookingStatus(bookingUpdateDto);
    }
}
