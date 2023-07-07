package ru.practicum.shereit.user.service;

import ru.practicum.shereit.user.model.User;

import java.util.List;

public interface UserService {

    User createNewUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(User user);

    User deleteUserById(Long id);
}
