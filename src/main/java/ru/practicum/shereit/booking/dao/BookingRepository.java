package ru.practicum.shereit.booking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.practicum.shereit.booking.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, BookingRepositoryCustom {

    @Query("\n" +
            "SELECT b\n" +
            "  FROM Booking AS b\n" +
            " WHERE (:state = 'ALL')\n" +
            "       OR (:state = 'CURRENT' AND b.booker.id = :userId AND b.status = 'APPROVED')\n" +
            "       OR (:state = 'PAST' AND b.booker.id = :userId AND b.end < :time)\n" +
            "       OR (:state = 'FUTURE' AND b.booker.id = :userId AND b.start > :time)\n" +
            "       OR (:state = 'WAITING' AND b.booker.id = :userId AND b.status = 'WAITING')\n" +
            "       OR (:state = 'REJECTED' AND b.booker.id = :userId AND b.status = 'REJECTED')\n" +
            " ORDER BY b.start DESC")
    List<Booking> findByBookerIdAndBookerState(
            @Param("userId") Long userId,
            @Param("state") String state,
            @Param("time") LocalDateTime localDateTime
    );

    @Query("\n" +
            "SELECT b\n" +
            "  FROM Booking AS b\n" +
            " WHERE (:state = 'ALL')\n" +
            "       OR (:state = 'CURRENT' AND b.item.owner.id = :userId AND b.status = 'APPROVED')\n" +
            "       OR (:state = 'PAST' AND b.item.owner.id = :userId AND b.end < :time)\n" +
            "       OR (:state = 'FUTURE' AND b.item.owner.id = :userId AND b.start > :time)\n" +
            "       OR (:state = 'WAITING' AND b.item.owner.id = :userId AND b.status = 'WAITING')\n" +
            "       OR (:state = 'REJECTED' AND b.item.owner.id = :userId AND b.status = 'REJECTED')\n" +
            " ORDER BY b.start DESC")
    List<Booking> findByOwnerIdAndBookerState(
            @Param("userId") Long userId,
            @Param("state") String state,
            @Param("time") LocalDateTime localDateTime
    );

    List<Booking> findAllByItemId(Long id);
}
