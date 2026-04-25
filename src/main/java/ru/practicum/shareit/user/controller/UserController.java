package ru.practicum.shareit.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.CreateNewUserDto;
import ru.practicum.shareit.user.dto.UpdateExistsUserDto;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final String idParamPath = "/{id}";

    @PostMapping
    public UserDto create(/*@Valid*/ @RequestBody CreateNewUserDto newUser, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return userService.createNewUser(newUser);
    }

    @PatchMapping(idParamPath)
    public UserDto update(@PathVariable("id") Long userId,
                          /*@Valid*/ @RequestBody UpdateExistsUserDto newUser, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return userService.updateExistsUser(userId, newUser);
    }

    @DeleteMapping(idParamPath)
    public boolean delete(@PathVariable("id") Long userId, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return userService.deleteUser(userId);
    }

    @GetMapping(idParamPath)
    public UserDto findUser(@PathVariable("id") Long userId, HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return userService.getUserById(userId);
    }

    @GetMapping
    public List<UserDto> getUsers(HttpServletRequest request) {
        System.out.println(">>> sout: " + request.getMethod().toString() + " " + request.getRequestURL().toString());
        return userService.getAllUsers();
    }

}
