package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.comment.dto.NewCommentDto;
import ru.practicum.shareit.item.dto.CreateNewItemDto;
import ru.practicum.shareit.item.dto.ExtendedItemDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.UpdateExistsItemDto;
import ru.practicum.shareit.item.service.ItemService;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final String idParamPath = "/{id}";

    @PostMapping
    public ItemDto createNewItem(@RequestHeader("X-Sharer-User-Id") Long ownerUserId,
                          @Valid @RequestBody CreateNewItemDto newItem) {
        return itemService.createNewItem(newItem, ownerUserId);
    }

    @GetMapping(idParamPath)
    public ItemDto getItemById(@PathVariable("id") Long itemId) {
        return itemService.getItemById(itemId);
    }

    @GetMapping("/search")
    public Collection<ItemDto> getItemsForRenter(@RequestParam(name = "text", defaultValue = "") String renterWishes) {
        return itemService.getItemsForRenter(renterWishes);
    }

    @GetMapping
    public List<ExtendedItemDto> findAll(@RequestHeader("X-Sharer-User-Id") Long ownerUserId) {
        //return itemService.findAll(ownerUserId);
        return itemService.getAllItemsByOwner(ownerUserId);
    }

    @PatchMapping(idParamPath)
    public ItemDto updateExistsItem(@PathVariable("id") Long itemId,
                          @Valid @RequestBody UpdateExistsItemDto newItem,
                          @RequestHeader("X-Sharer-User-Id") Long ownerUserId) {
        return itemService.updateExistsItem(newItem, ownerUserId, itemId);
    }

    @DeleteMapping(idParamPath)
    public void deleteItem(@PathVariable("id") Long itemId) {
        itemService.deleteItem(itemId);
    }

    @PostMapping(idParamPath + "/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(@PathVariable("item-id") Long itemId,
                                 @RequestHeader("X-Sharer-User-Id") Long userId,
                                 @Valid @RequestBody NewCommentDto comment) {
        return itemService.addComment(itemId, userId, comment);
    }

}
