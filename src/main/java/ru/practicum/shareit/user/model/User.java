package ru.practicum.shareit.user.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    Long id;
    @NotBlank(message = "@-адрес не должен быть пустым")
    @Email(message = "@-адрес должен быть в формате user@server.domain")
    String email;
    @NotBlank(message = "имя (логин) пользователя  не должно быть пустым")
    String name;
}

