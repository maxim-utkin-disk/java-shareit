package ru.practicum.shareit.comment.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NewCommentDto {

    @NotBlank(message = "Текст комментария не может быть пустым")
    private String text;
}