package ru.practicum.shareit.booking.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.user.mapper.UserMapper;

import ru.practicum.shareit.booking.dto.NewBookingDto;
import ru.practicum.shareit.booking.dto.UpdateBookingDto;
import ru.practicum.shareit.booking.model.Booking;
import ru.practicum.shareit.booking.model.BookingStatuses;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.user.model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BookingMapper {

    public static BookingDto mapToBookingDto(Booking booking) {
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setItem(ItemMapper.mapToItemDto(booking.getItem()));
        dto.setStart(booking.getStart());
        dto.setEnd(booking.getEnd());
        dto.setStatus(booking.getStatus());
        dto.setBooker(UserMapper.mapToUserDto(booking.getBooker()));

        return dto;
    }

    public static Booking mapToBooking(NewBookingDto request, User booker, Item item) {
        Booking booking = new Booking();
        booking.setItem(item);
        booking.setStart(request.getStart());
        booking.setEnd(request.getEnd());
        booking.setStatus(BookingStatuses.WAITING);
        booking.setBooker(booker);

        return booking;
    }

    public static Booking updateBookingFields(Booking booking, UpdateBookingDto request) {
        if (request.hasStart()) {
            booking.setStart(request.getStart());
        }

        if (request.hasEnd()) {
            booking.setEnd(request.getEnd());
        }

        booking.setStatus(request.getStatus());

        return booking;
    }
}

