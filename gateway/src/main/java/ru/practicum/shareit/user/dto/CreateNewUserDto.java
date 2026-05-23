package ru.practicum.shareit.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateNewUserDto {

    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Email должен быть в формате user@server.domain")
    private String email;

    @NotBlank(message = "Имя пользователя не должно быть пустым")
    private String name;
}