package ru.practicum.shareit.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewCommentDto {
    @NotBlank(message = "Текст комментария не может быть пустым")
    String text;

    @NotNull(message = "Требуется указать id комментируемого предмета. Он не может быть пустым (NULL)!")
    Long itemId;

    @NotNull(message = "Требуется указать id пользователя-комментатора. Он не может быть пустым (NULL)!")
    Long authorId;
}
