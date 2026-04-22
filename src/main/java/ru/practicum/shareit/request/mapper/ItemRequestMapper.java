package ru.practicum.shareit.request.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.dto.NewItemRequestDto;
import ru.practicum.shareit.request.dto.UpdateItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.user.model.User;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ItemRequestMapper {

    public static ItemRequestDto mapToItemRequestDto(ItemRequest itemRequest) {
        ItemRequestDto dto = new ItemRequestDto();
        dto.setId(itemRequest.getId());
        dto.setDescription(itemRequest.getDescription());
        dto.setRequestorId(itemRequest.getRequestor().getId());
        dto.setCreated(itemRequest.getCreated());
        return dto;
    }

    public static ItemRequest mapToItemRequest(NewItemRequestDto newItemRequest, User findUser) {
        ItemRequest itemRequest = new ItemRequest();
        itemRequest.setDescription(newItemRequest.getDescription());
        itemRequest.setRequestor(findUser);
        itemRequest.setCreated(newItemRequest.getCreated());
        return itemRequest;
    }

    public static ItemRequest updateItemFields(ItemRequest itemRequest, UpdateItemRequestDto updateItemRequest, User findUser) {
        itemRequest.setDescription(updateItemRequest.getDescription());
        return itemRequest;
    }
}

