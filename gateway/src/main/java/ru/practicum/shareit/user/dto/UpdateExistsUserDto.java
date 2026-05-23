package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateExistsUserDto {

    @Email(message = "Email должен быть в формате user@server.domain")
    private String email;

    private String name;
}