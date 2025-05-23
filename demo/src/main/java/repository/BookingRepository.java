package repository;

import model.Booking;
import model.BookingStatus;
import model.Client;
import model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT b FROM Booking b WHERE " +
            "b.room.id = :roomId AND " +
            "b.status = :status AND " +
            "b.checkInDate < :checkOut AND " +
            "b.checkOutDate > :checkIn")
    List<Booking> findOverlappingBookings(
            @Param("roomId") Long roomId,
            @Param("checkIn") LocalDate checkIn,
            @Param("checkOut") LocalDate checkOut,
            @Param("status") BookingStatus status);

    List<Booking> findByClient(Client client);
    List<Booking> findByRoom(Room room);
    List<Booking> findByStatus(BookingStatus status);
}
