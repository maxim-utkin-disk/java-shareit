package ru.practicum.shareit.item.service;

import ru.practicum.shareit.comment.dto.CommentDto;
import ru.practicum.shareit.comment.dto.NewCommentDto;
import ru.practicum.shareit.item.dto.CreateNewItemDto;
import ru.practicum.shareit.item.dto.ExtendedItemDto;
import ru.practicum.shareit.item.dto.UpdateExistsItemDto;
import ru.practicum.shareit.item.dto.ItemDto;

import java.util.Collection;
import java.util.List;

public interface ItemService {

    ItemDto createNewItem(CreateNewItemDto newItem, Long ownerUserId);

    ItemDto updateExistsItem(UpdateExistsItemDto newItem, Long ownerUserId, Long itemId);

    void deleteItem(Long itemId);

    boolean deleteItemsByOwner(Long ownerUserId);

    ItemDto getItemById(Long itemId);

    List<ItemDto> getAllItems();

    List<ItemDto> getAllItemsByOwner(Long ownerUserId);
    List<ExtendedItemDto> findAll(Long ownerId);

    List<ItemDto> getItemsForRenter(String renterWishes);

    CommentDto addComment(Long itemId, Long userId, NewCommentDto comment);

    // -- ** --

    AdvancedItemDto findItem(Long ownerId, Long itemId);


    ItemDto update(Long itemId, UpdateItemRequest request, Long ownerId);

    void delete(Long ownerId, Long itemId);




}
