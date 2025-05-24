package com.example.demo.service;

import com.example.demo.model.Booking;
import com.example.demo.model.Room;
import com.example.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    public boolean isAvailable(Room room, LocalDate start, LocalDate end) {
        return !bookingRepository.existsByRoomAndStartDateLessThanEqualAndEndDateGreaterThanEqual(room, end, start);
    }

    public Booking save(Booking booking) {
        if (!isAvailable(booking.getRoom(), booking.getStartDate(), booking.getEndDate())) {
            throw new RuntimeException("Комната уже забронирована!");
        }
        return bookingRepository.save(booking);
    }
}
