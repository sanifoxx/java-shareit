package ru.practicum.shereit.user.dao;

import ru.practicum.shereit.user.model.User;

public interface UserRepositoryCustom {

    User findByIdOrElseThrow(Long id);
}
