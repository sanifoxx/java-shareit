package ru.practicum.shereit.user.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shereit.user.dto.UserDto;
import ru.practicum.shereit.user.mapper.UserMapper;
import ru.practicum.shereit.user.model.User;
import ru.practicum.shereit.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody @Valid User user) {
        log.info("POST /users | User-object: {}", user);
        @Valid @NotNull String name = user.getName();
        @Valid @NotNull String email = user.getEmail();
        return userService.createNewUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("GET /users");
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable("userId") Long id) {
        log.info("GET /users/{}", id);
        return userService.getUserById(id);
    }

    @PatchMapping("/{userId}")
    public User updateUser(@RequestBody User user,
                           @PathVariable Long userId) {
        log.info("PATCH /users/{} | User-object: {}", userId,user);
        user.setId(userId);
        return userService.updateUser(user);
    }

    @DeleteMapping("/{userId}")
    public UserDto deleteUserById(@PathVariable("userId") Long id) {
        log.info("DELETE /users/{}", id);
        return UserMapper.toDto(userService.deleteUserById(id));
    }
}
