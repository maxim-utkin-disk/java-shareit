package ru.practicum.shareit.booking.dto;

/*import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;*/
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewBookingDto {

    //@FutureOrPresent(message = "Дата начала бронирования должна быть равна текущей дате или позже")
    LocalDateTime start;

    //@Future(message = "Дата заверщения бронирования должна быть позже текущей даты")
    LocalDateTime end;

    //NotNull(message = "Требуется указать id предмета бронирования")
    Long itemId;

    Long bookerId;
}

