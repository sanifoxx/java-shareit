package ru.practicum.shereit.user.service;

import ru.practicum.shereit.user.dto.UserCreateDto;
import ru.practicum.shereit.user.dto.UserDto;
import ru.practicum.shereit.user.dto.UserUpdateDto;

import java.util.List;

public interface UserService {

    UserCreateDto createNewUser(UserCreateDto userCreateDto);

    List<UserDto> getAllUsers();

    UserDto getUserById(Long id);

    UserUpdateDto updateUser(UserUpdateDto userUpdateDto);

    UserDto deleteUserById(Long id);
}
