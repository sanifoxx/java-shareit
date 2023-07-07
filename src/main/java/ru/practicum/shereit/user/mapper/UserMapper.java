package ru.practicum.shereit.user.mapper;

import ru.practicum.shereit.user.dto.UserDto;
import ru.practicum.shereit.user.model.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public static User toUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .build();
    }
}
