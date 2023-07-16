package ru.practicum.shereit.user.controller;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shereit.user.dto.UserCreateDto;
import ru.practicum.shereit.user.dto.UserDto;
import ru.practicum.shereit.user.dto.UserUpdateDto;
import ru.practicum.shereit.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserCreateDto createUser(@RequestBody @Valid UserCreateDto userCreateDto) {
        log.info("POST /users | UserCreateDto-object: {}", userCreateDto);
        return userService.createNewUser(userCreateDto);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        log.info("GET /users");
        return userService.getAllUsers();
    }

    @GetMapping("/{userId}")
    public UserDto getUserById(@PathVariable("userId") Long id) {
        log.info("GET /users/{}", id);
        return userService.getUserById(id);
    }

    @PatchMapping("/{userId}")
    public UserUpdateDto updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto,
                                    @PathVariable Long userId) {
        log.info("PATCH /users/{} | User-object: {}", userId, userUpdateDto);
        userUpdateDto.setId(userId);
        return userService.updateUser(userUpdateDto);
    }

    @DeleteMapping("/{userId}")
    public UserDto deleteUserById(@PathVariable("userId") Long id) {
        log.info("DELETE /users/{}", id);
        return userService.deleteUserById(id);
    }
}
