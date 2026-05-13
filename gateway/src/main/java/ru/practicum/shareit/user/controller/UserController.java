package ru.practicum.shareit.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.client.UserClient;
import ru.practicum.shareit.user.dto.CreateNewUserDto;
import ru.practicum.shareit.user.dto.UpdateExistsUserDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserClient userClient;

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody CreateNewUserDto dto) {
        return userClient.createUser(dto);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> update(@PathVariable long userId,
                                         @RequestBody UpdateExistsUserDto dto) {
        return userClient.updateUser(userId, dto);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> get(@PathVariable long userId) {
        return userClient.getUser(userId);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return userClient.getAllUsers();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> delete(@PathVariable long userId) {
        return userClient.deleteUser(userId);
    }
}