package ru.practicum.shareit.request.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.NewItemRequestDto;
import ru.practicum.shareit.request.dto.UpdateItemRequestDto;
import ru.practicum.shareit.request.service.ItemRequestService;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/requests")
public class ItemRequestController {
    private final ItemRequestService itemRequestService;
    private final String id = "/{request-id}";

    @GetMapping(id)
    public ItemRequestDto findItemRequest(@PathVariable("request-id") Long requestId) {
        return itemRequestService.findItemRequest(requestId);
    }

    @GetMapping("/all")
    public Collection<ItemRequestDto> findAllOfAnotherRequestors(@RequestHeader("X-Sharer-User-Id") Long requestorId) {
        return itemRequestService.findAllOfAnotherRequestors(requestorId);
    }

    /*@GetMapping
    public Collection<ItemRequestDto> findAll() {
        return itemRequestService.findAll();
    }
*/

    @GetMapping
    public Collection<ItemRequestDto> findAllByRequestorId(@RequestHeader("X-Sharer-User-Id") Long requestorId) {
        return itemRequestService.findAllByRequestorId(requestorId);
    }



    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ItemRequestDto create(@RequestHeader("X-Sharer-User-Id") Long userId,
                                 @RequestBody NewItemRequestDto itemRequest) {
        return itemRequestService.create(userId, itemRequest);
    }

    @PutMapping(id)
    public ItemRequestDto update(@PathVariable("request-id") Long requestId,
                                 @RequestHeader("X-Sharer-User-Id") Long userId,
                                 @RequestBody UpdateItemRequestDto updItemRequest) {
        return itemRequestService.update(requestId, userId, updItemRequest);
    }

    @DeleteMapping(id)
    public void delete(@PathVariable("request-id") Long requestId) {
        itemRequestService.delete(requestId);
    }
}
