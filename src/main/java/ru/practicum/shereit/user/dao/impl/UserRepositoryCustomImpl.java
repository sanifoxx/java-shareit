package ru.practicum.shereit.user.dao.impl;

import org.springframework.context.annotation.Lazy;
import ru.practicum.shereit.error_handler.model.exception.not_found.UserNotFoundException;
import ru.practicum.shereit.user.dao.UserRepository;
import ru.practicum.shereit.user.dao.UserRepositoryCustom;
import ru.practicum.shereit.user.model.User;

public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private final UserRepository userRepository;

    public UserRepositoryCustomImpl(@Lazy UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByIdOrElseThrow(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                String.format("User with id '%d' not found", id)
        ));
    }
}
