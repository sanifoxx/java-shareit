package ru.practicum.shereit.user.dao;

import ru.practicum.shereit.user.model.User;

import java.util.List;

public interface UserRepository {

    User createNewUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User updateUser(User user);

    User deleteUserById(Long id);
}
