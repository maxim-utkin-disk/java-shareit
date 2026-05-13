package ru.practicum.shareit.item.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    @NotBlank(message = "Наименование предмета бронирования не должно быть пустым")
    String name;
    @NotBlank(message = "Описание предмета бронирования не должно быть пустым")
    String description;
    Boolean available = Boolean.FALSE;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long owner;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long requestId;
}
