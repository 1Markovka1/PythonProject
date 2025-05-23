package repository;

import model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    // Поиск комнаты по номеру
    Optional<Room> findByRoomNumber(String roomNumber);

    // Поиск доступных комнат определенного типа
    List<Room> findByRoomTypeAndIsAvailableTrue(String roomType);

    // Поиск всех доступных комнат
    List<Room> findByIsAvailableTrue();

    // Поиск комнат, не занятых в указанный период
    @Query("SELECT r FROM Room r WHERE r.isAvailable = true AND r.id NOT IN " +
            "(SELECT b.room.id FROM Booking b WHERE " +
            "b.checkInDate < :checkOut AND b.checkOutDate > :checkIn AND b.status = 'CONFIRMED')")
    List<Room> findAvailableRoomsForPeriod(
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut);

    // Поиск комнат по цене (меньше или равно указанной)
    List<Room> findByPricePerNightLessThanEqual(BigDecimal maxPrice);
}
