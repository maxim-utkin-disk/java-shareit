package ru.practicum.shareit.user.model;

import jakarta.persistence.*;
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
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*private*/ Long id;

    @NotBlank(message = "@-адрес не должен быть пустым")
    @Column(length = 100, nullable = false, unique = true)
    @Email(message = "@-адрес должен быть в формате user@server.domain")
    /*private*/ String email;

    @NotBlank(message = "имя (логин) пользователя  не должно быть пустым")
    @Column(length = 100, nullable = false)
    /*private*/ String name;

}

