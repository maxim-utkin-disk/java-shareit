package ru.practicum.shareit.request.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.NewItemRequestDto;
import ru.practicum.shareit.request.dto.UpdateItemRequestDto;
import ru.practicum.shareit.request.service.ItemRequestServiceImpl;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/requests")
public class ItemRequestController {
    private final ItemRequestServiceImpl itemRequestService;
    private final String id = "/{request-id}";

    @GetMapping(id)
    public ItemRequestDto findItemRequest(@PathVariable("request-id") Long requestId, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return itemRequestService.findItemRequest(requestId);
    }

    @GetMapping
    public Collection<ItemRequestDto> findAll() {
        return itemRequestService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemRequestDto create(@RequestHeader("X-Sharer-User-Id") Long userId,
                                 /*@Valid*/ @RequestBody NewItemRequestDto itemRequest, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return itemRequestService.create(userId, itemRequest);
    }

    @PutMapping(id)
    public ItemRequestDto update(@PathVariable("request-id") Long requestId,
                                 @RequestHeader("X-Sharer-User-Id") Long userId,
                                 /*@Valid*/ @RequestBody UpdateItemRequestDto updItemRequest, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return itemRequestService.update(requestId, userId, updItemRequest);
    }

    @DeleteMapping(id)
    public void delete(@PathVariable("request-id") Long requestId, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        itemRequestService.delete(requestId);
    }
}
