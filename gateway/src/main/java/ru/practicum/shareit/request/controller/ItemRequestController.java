package ru.practicum.shareit.request.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.client.ItemRequestClient;
import ru.practicum.shareit.request.dto.NewItemRequestDto;

@RequiredArgsConstructor
@RestController
@RequestMapping("/requests")
public class ItemRequestController {

    private final ItemRequestClient itemRequestClient;

    @PostMapping
    public ResponseEntity<Object> create(
            @RequestHeader("X-Sharer-User-Id") long userId,
            @Valid @RequestBody NewItemRequestDto dto) {
        return itemRequestClient.create(userId, dto);
    }

    @GetMapping
    public ResponseEntity<Object> findAllByRequestor(
            @RequestHeader("X-Sharer-User-Id") long userId) {
        return itemRequestClient.findAllByRequestor(userId);
    }

    @GetMapping("/all")
    public ResponseEntity<Object> findAllOthers(
            @RequestHeader("X-Sharer-User-Id") long userId) {
        return itemRequestClient.findAllOthers(userId);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Object> findItemRequest(
            @RequestHeader("X-Sharer-User-Id") long userId,
            @PathVariable long requestId) {
        return itemRequestClient.findItemRequest(userId, requestId);
    }
}