package ru.practicum.shereit.user.mapper;

import ru.practicum.shereit.user.dto.UserCreateDto;
import ru.practicum.shereit.user.dto.UserDto;
import ru.practicum.shereit.user.dto.UserUpdateDto;
import ru.practicum.shereit.user.model.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public static UserCreateDto toCreateDto(User user) {
        return UserCreateDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static UserUpdateDto toUpdateDto(User user) {
        return UserUpdateDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    public static User toUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .build();
    }

    public static User toUser(UserCreateDto userCreateDto) {
        return User.builder()
                .id(userCreateDto.getId())
                .name(userCreateDto.getName())
                .email(userCreateDto.getEmail())
                .build();
    }

    public static User toUser(UserUpdateDto userUpdateDto) {
        return User.builder()
                .id(userUpdateDto.getId())
                .name(userUpdateDto.getName())
                .email(userUpdateDto.getEmail())
                .build();
    }
}
