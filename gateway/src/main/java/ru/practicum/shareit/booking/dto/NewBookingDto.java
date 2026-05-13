package ru.practicum.shareit.booking.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NewBookingDto {

    @FutureOrPresent(message = "Дата начала должна быть не раньше текущей")
    @NotNull
    private LocalDateTime start;

    @Future(message = "Дата окончания должна быть в будущем")
    @NotNull
    private LocalDateTime end;

    @NotNull(message = "Требуется указать id вещи")
    private Long itemId;
}