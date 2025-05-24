package com.example.demo.repository;

import com.example.demo.model.Booking;
import com.example.demo.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsByRoomAndStartDateLessThanEqualAndEndDateGreaterThanEqual(Room room, LocalDate end, LocalDate start);
}
