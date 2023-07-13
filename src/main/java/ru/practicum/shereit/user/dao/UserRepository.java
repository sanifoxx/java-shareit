package ru.practicum.shereit.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.shereit.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
