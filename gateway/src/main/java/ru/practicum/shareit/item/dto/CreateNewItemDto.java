package ru.practicum.shareit.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateNewItemDto {
    @NotBlank(message = "Название не должно быть пустым")
    private String name;

    @NotBlank(message = "Описание не должно быть пустым")
    private String description;

    @NotNull(message = "Укажите доступность вещи")
    private Boolean available;

    private Long requestId; // опциональное поле
}