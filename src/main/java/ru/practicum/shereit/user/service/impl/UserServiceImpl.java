package ru.practicum.shereit.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shereit.error_handler.model.exception.already_exists.UserAlreadyExistsException;
import ru.practicum.shereit.error_handler.model.exception.not_found.UserNotFoundException;
import ru.practicum.shereit.user.dto.UserCreateDto;
import ru.practicum.shereit.user.dto.UserDto;
import ru.practicum.shereit.user.dto.UserUpdateDto;
import ru.practicum.shereit.user.mapper.UserMapper;
import ru.practicum.shereit.user.model.User;
import ru.practicum.shereit.user.dao.UserRepository;
import ru.practicum.shereit.user.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Qualifier("InMemoryUserRepository")
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserCreateDto createNewUser(UserCreateDto userCreateDto) {
        User user = UserMapper.toUser(userCreateDto);
        return UserMapper.toCreateDto(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findByIdOrElseThrow(id);
        return UserMapper.toDto(user);
    }

    @Transactional
    @Override
    public UserUpdateDto updateUser(UserUpdateDto userUpdateDto) {
        User oldUser = userRepository.findByIdOrElseThrow(userUpdateDto.getId());
        if (userRepository.existsUserByEmailAndIdNot(userUpdateDto.getEmail(), userUpdateDto.getId())) {
            throw new UserAlreadyExistsException(
                    String.format("User with email '%s' already exists", userUpdateDto.getEmail())
            );
        }
        if (userUpdateDto.getName() != null) {
            oldUser.setName(userUpdateDto.getName());
        }
        if (userUpdateDto.getEmail() != null) {
            oldUser.setEmail(userUpdateDto.getEmail());
        }
        return UserMapper.toUpdateDto(userRepository.save(oldUser));
    }

    @Transactional
    @Override
    public UserDto deleteUserById(Long id) {
        User user = userRepository.findByIdOrElseThrow(id);
        userRepository.deleteById(id);
        return UserMapper.toDto(user);
    }
}
