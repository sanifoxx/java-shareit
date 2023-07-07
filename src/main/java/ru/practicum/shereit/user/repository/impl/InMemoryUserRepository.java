package ru.practicum.shereit.user.repository.impl;

import org.springframework.stereotype.Component;
import ru.practicum.shereit.error_handler.model.exception.already_exists.UserAlreadyExistsException;
import ru.practicum.shereit.error_handler.model.exception.not_found.UserNotFoundException;
import ru.practicum.shereit.user.model.User;
import ru.practicum.shereit.user.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component("InMemoryUserRepository")
public class InMemoryUserRepository implements UserRepository {

    private static Long globalId = 0L;

    private final List<User> users = new ArrayList<>();

    @Override
    public User createNewUser(User user) {
        otherUserWithEmailIsNotExistOrElseThrow(user);
        user.setId(getId());
        users.add(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(Long id) {
        return users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException(
                        String.format("User with id '%d' not found", id)
                ));
    }

    @Override
    public User updateUser(User user) {
        otherUserWithEmailIsNotExistOrElseThrow(user);
        User memoryUser = getUserById(user.getId());
        memoryUser.setName(
                user.getName() == null || user.getName().isBlank()
                        ? memoryUser.getName()
                        : user.getName()
        );
        memoryUser.setEmail(
                user.getEmail() == null || user.getEmail().isBlank()
                        ? memoryUser.getEmail()
                        : user.getEmail()
        );
        return getUserById(user.getId());
    }

    @Override
    public User deleteUserById(Long id) {
        User resultUser = getUserById(id);
        users.remove(resultUser);
        return resultUser;
    }

    private void otherUserWithEmailIsNotExistOrElseThrow(User user) {
        if (users.stream().anyMatch(x -> x.getEmail().equals(user.getEmail()) && !x.getId().equals(user.getId()))) {
            throw new UserAlreadyExistsException(
                    String.format("User with email '%s' already exists", user.getEmail())
            );
        }
    }

    private Long getId() {
        return ++globalId;
    }
}
