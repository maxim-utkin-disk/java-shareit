package ru.practicum.shareit.item.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping(path = "/items")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;
    private final String idParamPath = "/{itemId}";
    //private final String idParamPathComment = idParamPath + "/comment";

    @PostMapping("/{itemId}/comment")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto addComment(@PathVariable("itemId") Long itemId,
                                 @RequestHeader("X-Sharer-User-Id") Long userId,
                                 /*@Valid*/ @RequestBody NewCommentDto comment, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return itemService.addComment(itemId, userId, comment);
    }

    @PostMapping
    public ItemDto createNewItem(@RequestHeader("X-Sharer-User-Id") Long ownerUserId,
                          /*@Valid*/ @RequestBody CreateNewItemDto newItem,
                                 HttpServletRequest request
                                 ) {
        ///log.error(" >>>> !!!! >>>>" + request.getRequestURL());
        //System.out.println(" >>>> !!!! >>>>" + request.getRequestURL());
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return itemService.createNewItem(newItem, ownerUserId);
    }

    @GetMapping("/search")
    public Collection<ItemDto> getItemsForRenter(@RequestParam(name = "text", defaultValue = "") String renterWishes, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return itemService.getItemsForRenter(renterWishes);
    }

    @GetMapping(idParamPath)
    public ExtendedItemDto getItemById(@PathVariable("itemId") Long itemId, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return itemService.getItemById(itemId);
    }

    @GetMapping
    public List<ExtendedItemDto> findAll(@RequestHeader("X-Sharer-User-Id") Long ownerUserId, HttpServletRequest request) {
        //return itemService.findAll(ownerUserId);
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return itemService.getAllItemsByOwner(ownerUserId);
    }

    @PatchMapping(idParamPath)
    public ItemDto updateExistsItem(@PathVariable("itemId") Long itemId,
                          /*@Valid*/ @RequestBody UpdateExistsItemDto newItem,
                          @RequestHeader("X-Sharer-User-Id") Long ownerUserId, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return itemService.updateExistsItem(newItem, ownerUserId, itemId);
    }

    @DeleteMapping(idParamPath)
    public void deleteItem(@PathVariable("itemId") Long itemId, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        itemService.deleteItem(itemId);
    }

}
