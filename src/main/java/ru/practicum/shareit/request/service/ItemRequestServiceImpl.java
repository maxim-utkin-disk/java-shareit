package ru.practicum.shareit.request.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.ValidationException;
import ru.practicum.shareit.request.dto.ItemRequestDto;
import ru.practicum.shareit.request.mapper.ItemRequestMapper;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

import ru.practicum.shareit.request.dto.NewItemRequestDto;
import ru.practicum.shareit.request.dto.UpdateItemRequestDto;
import ru.practicum.shareit.request.model.ItemRequest;
import ru.practicum.shareit.request.repository.ItemRequestRepository;
import ru.practicum.shareit.user.model.User;

@Slf4j
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ItemRequestServiceImpl implements ItemRequestService {
    ItemRequestRepository itemRequestRepository;
    UserRepository userRepository;

    @Autowired
    public ItemRequestServiceImpl(ItemRequestRepository itemRequestRepository, UserRepository userRepository) {
        this.itemRequestRepository = itemRequestRepository;
        this.userRepository = userRepository;
    }

    private ItemRequest findById(Long itemRequestId) {
        return itemRequestRepository.findById(itemRequestId)
                .orElseThrow(() -> new NotFoundException(String.format("Запрос c ID %d не найден", itemRequestId)));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Владелец вещи c ID %d не найден", userId)));
    }

    @Override
    @Transactional
    public ItemRequestDto create(Long userId, NewItemRequestDto newItemRequestDto) {
        log.debug("Создаем запись о запросе");

        User findUser = findUserById(userId);

        ItemRequest itemRequest = ItemRequestMapper.mapToItemRequest(newItemRequestDto, findUser);
        itemRequest = itemRequestRepository.save(itemRequest);

        return ItemRequestMapper.mapToItemRequestDto(itemRequest);
    }

    @Override
    public ItemRequestDto findItemRequest(Long itemRequestId) {
        return ItemRequestMapper.mapToItemRequestDto(findById(itemRequestId));
    }

    @Override
    public Collection<ItemRequestDto> findAll() {
        log.debug("Получаем записи о всех запросах");
        return itemRequestRepository.findAll()
                .stream()
                .map(ItemRequestMapper::mapToItemRequestDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ItemRequestDto update(Long requestId, Long userId, UpdateItemRequestDto updateItemRequestDto) {
        log.debug("Обновляем данные запроса");

        User findUser = findUserById(userId);

        if (requestId == null) {
            throw new ValidationException("ID запроса должен быть указан");
        }

        ItemRequest updatedItem = ItemRequestMapper.updateItemFields(findById(requestId), updateItemRequestDto, findUser);
        updatedItem = itemRequestRepository.save(updatedItem);

        return ItemRequestMapper.mapToItemRequestDto(updatedItem);
    }

    @Override
    @Transactional
    public void delete(Long itemRequestId) {
        ItemRequest itemRequest = findById(itemRequestId);
        log.debug("Удаляем данные запроса с ID {}", itemRequest.getId());
        itemRequestRepository.delete(itemRequest);
    }
}

