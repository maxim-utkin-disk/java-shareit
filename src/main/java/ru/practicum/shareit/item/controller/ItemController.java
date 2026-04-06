package ru.practicum.shareit.item.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CreateNewItemDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.UpdateExistsItemDto;
import ru.practicum.shareit.item.service.ItemService;

import java.util.Collection;

/**
 * TODO Sprint add-controllers.
 */
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final String idParamPath = "/{id}";

    @PostMapping
    public ItemDto create(@RequestHeader("X-Sharer-User-Id") Long ownerUserId,
                          @Valid @RequestBody CreateNewItemDto newItem) {
        return itemService.createNewItem(newItem, ownerUserId);
    }

    @GetMapping(idParamPath)
    public ItemDto findItem(@RequestHeader("X-Sharer-User-Id") Long ownerUserId,
                            @PathVariable("id") Long itemId) {
        return itemService.getItemById(itemId);
    }

    @GetMapping("/search")
    public Collection<ItemDto> findItemsForTenant(@RequestHeader("X-Sharer-User-Id") Long ownerUserId,
                                                  @RequestParam(name = "text", defaultValue = "") String renterWishes) {
        return itemService.getItemsForRent(renterWishes);
    }

    @GetMapping
    public Collection<ItemDto> findAll(@RequestHeader("X-Sharer-User-Id") Long ownerUserId) {
        return itemService.getAllItemsByOwner(ownerUserId);
    }

    @PatchMapping(idParamPath)
    public ItemDto update(@PathVariable("id") Long itemId,
                          @Valid @RequestBody UpdateExistsItemDto newItem,
                          @RequestHeader("X-Sharer-User-Id") Long ownerUserId) {
        return itemService.updateExistsItem(newItem, ownerUserId, itemId);
    }

    @DeleteMapping(idParamPath)
    public boolean delete(@RequestHeader("X-Sharer-User-Id") Long ownerId,
                          @PathVariable("id") Long itemId) {
        return itemService.deleteItem(itemId);
    }

}
