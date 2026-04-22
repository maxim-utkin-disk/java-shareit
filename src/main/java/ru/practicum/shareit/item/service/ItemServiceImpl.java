package ru.practicum.shareit.item.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.repository.BookingRepository;
import ru.practicum.shareit.comment.repository.CommentRepository;
import ru.practicum.shareit.exception.NotFoundException;
import ru.practicum.shareit.exception.OtherOwnerItemEditingException;
import ru.practicum.shareit.item.dto.CreateNewItemDto;
import ru.practicum.shareit.item.dto.ItemDto;
import ru.practicum.shareit.item.dto.UpdateExistsItemDto;
import ru.practicum.shareit.item.mapper.ItemMapper;
import ru.practicum.shareit.item.model.Item;
import ru.practicum.shareit.item.repository.ItemRepository;
//import ru.practicum.shareit.item.repository.ItemStorage;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;
//import ru.practicum.shareit.user.repository.UserStorage;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ItemServiceImpl implements ItemService {
    //ItemStorage itemStorage;
    //UserStorage userStorage;
    ItemRepository itemRepository;
    UserRepository userRepository;
    BookingRepository bookingRepository;
    CommentRepository commentRepository;

    @Autowired
    public ItemServiceImpl(/*@Qualifier("ItemStorageInMemory") ItemStorage itemStorage,
                           @Qualifier("UserStorageInMemory") UserStorage userStorage*/
            ItemRepository itemRepository,
            UserRepository userRepository,
            BookingRepository bookingRepository,
            CommentRepository commentRepository

    ) {
        /*this.itemStorage = itemStorage;
        this.userStorage = userStorage;*/
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.commentRepository = commentRepository;

    }

    private Item findById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException(String.format("Предмет брониования id = %d не найден", itemId)));
    }

    private User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(String.format("Собственник предмета бронирования id = %d не найден", userId)));
    }

    @Override
    @Transactional
    public ItemDto createNewItem(CreateNewItemDto newItem, Long ownerUserId) {
        log.debug("Добавление нового предмета бронирования");
        //User user = userStorage.selectOne(ownerUserId);
        User findUser = findUserById(ownerUserId);
        //Item item = ItemMapper.mapToItem(newItem, ownerUserId);
        Item item = ItemMapper.mapToItem(newItem, findUser);
        //item = itemStorage.insert(item);
        item = itemRepository.save(item);
        return ItemMapper.mapToItemDto(item);
    }

    public ItemDto updateExistsItem(UpdateExistsItemDto newItem, Long ownerUserId, Long itemId) {
        log.debug("Обновление предмета бронирования id = {}", itemId);
        Item item = itemStorage.selectOne(itemId);

        if (!item.getOwner().equals(ownerUserId)) {
            throw new OtherOwnerItemEditingException("Изменять предмет бронирования может только его владелец");
        }

        Item updatedItem = itemStorage.update(ItemMapper.updateItemFields(item, newItem));
        return ItemMapper.mapToItemDto(updatedItem);
    }

    @Override
    @Transactional
    public void deleteItem(Long itemId) {
        Item item = findById(itemId);
        log.debug("Удаление предмета бронирования {}", item.toString());
        itemRepository.delete(item);
    }

    public boolean deleteItemsByOwner(Long ownerUserId) {
        log.debug("Удаление всех предметов бронирования по владельцу id = {}", ownerUserId);
//        User user = userStorage.selectOne(ownerUserId);
//        return itemStorage.deleteByOwner(ownerUserId);
        return true;
    }

    public ItemDto getItemById(Long itemId) {
        return ItemMapper.mapToItemDto(findById(itemId));
    }

//    public List<ItemDto> getAllItems() {
//        log.debug("Получение всех записей обо всех предметах бронирования всех владельцев");
//        return itemStorage.selectAll().stream().map(ItemMapper::mapToItemDto).toList();
//    }

    public List<ItemDto> getAllItemsByOwner(Long ownerUserId) {
        log.debug("Получение записей обо всех предметах бронирования по указанному владельцу");
        return itemStorage.selectAllItemsByOwner(ownerUserId).stream().map(ItemMapper::mapToItemDto).toList();
    }

    public List<ItemDto> getItemsForRenter(String renterWishes) {
        log.debug("Поиск предметов бронирования по ключевым словам \"{}\"", renterWishes);
        if (renterWishes.isEmpty()) {
            return new ArrayList<>();
        }

        return itemStorage.getItemsForRenter(renterWishes.toLowerCase()).stream()
                .map(ItemMapper::mapToItemDto)
                .toList();


    }




}
