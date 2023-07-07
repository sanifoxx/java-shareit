package ru.practicum.shereit.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.practicum.shereit.user.model.User;
import ru.practicum.shereit.user.repository.UserRepository;
import ru.practicum.shereit.user.service.UserService;

import java.util.List;

@Service
@Qualifier("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Qualifier("InMemoryUserRepository")
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createNewUser(User user) {
        return userRepository.createNewUser(user);
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public User getUserById(Long id) {
        return userRepository.getUserById(id);
    }

    public User updateUser(User user) {
        getUserById(user.getId());
        return userRepository.updateUser(user);
    }

    public User deleteUserById(Long id) {
        getUserById(id);
        return userRepository.deleteUserById(id);
    }
}
