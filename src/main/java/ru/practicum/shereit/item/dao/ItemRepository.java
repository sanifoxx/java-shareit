package ru.practicum.shereit.item.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.shereit.item.model.Item;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> getItemsByOwnerId(Long userId);

    @Query("\n" +
            "SELECT i\n" +
            "  FROM Item i\n" +
            " WHERE i.available = true\n" +
            "       AND (LOWER(i.name) LIKE LOWER(CONCAT('%', :query, '%'))\n" +
            "       OR LOWER(i.description) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Item> getAvailableItemsByQuery(@Param("query") String query);
}
