package ru.practicum.shareit.booking.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.service.BookingServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.NewBookingDto;
import ru.practicum.shareit.booking.dto.UpdateBookingDto;

import java.util.Collection;


@RestController
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingServiceImpl bookingService;
    private final String id = "/{booking-id}";
    private final String owner = "/owner";

    @GetMapping(id)
    public BookingDto findBooking(@RequestHeader("X-Sharer-User-Id") Long userId,
                                  @PathVariable("booking-id") Long bookingId, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return bookingService.findBooking(bookingId, userId);
    }

    @GetMapping
    public Collection<BookingDto> findAllBookingsByUser(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                        @RequestParam(name = "state", defaultValue = "ALL") String state, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return bookingService.findAllBookingsByUser(userId, state);
    }

    @GetMapping(owner)
    public Collection<BookingDto> findAllBookingsByOwnerItems(@RequestHeader("X-Sharer-User-Id") Long userId,
                                                              @RequestParam(name = "state", defaultValue = "ALL") String state, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return bookingService.findAllBookingsByOwnerItems(userId, state);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookingDto create(@RequestHeader("X-Sharer-User-Id") Long userId,
                             /*@Valid*/ @RequestBody NewBookingDto booking, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return bookingService.create(userId, booking);
    }

    @PutMapping(id)
    public BookingDto update(/*@Valid*/ @RequestBody UpdateBookingDto newBooking, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return bookingService.update(newBooking);
    }

    @DeleteMapping(id)
    public void delete(@PathVariable("booking-id") Long bookingId, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        bookingService.delete(bookingId);
    }

    @PatchMapping(id)
    public BookingDto approveBooking(@PathVariable("booking-id") Long bookingId,
                                     @RequestHeader("X-Sharer-User-Id") Long userId,
                                     @RequestParam(name = "approved", defaultValue = "false") Boolean approved, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return bookingService.approveBooking(bookingId, userId, approved);
    }
}
