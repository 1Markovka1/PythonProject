package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.model.Client;
import com.example.demo.model.Room;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.ClientRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingRestController {

    private final BookingRepository bookingRepository;
    private final ClientRepository clientRepository;
    private final RoomRepository roomRepository;

    public BookingRestController(BookingRepository bookingRepository, ClientRepository clientRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody Booking booking) {
        if (booking.getClient() != null && booking.getClient().getId() != null) {
            Client client = clientRepository.findById(booking.getClient().getId()).orElse(null);
            booking.setClient(client);
        }

        if (booking.getRoom() != null && booking.getRoom().getId() != null) {
            Room room = roomRepository.findById(booking.getRoom().getId()).orElse(null);
            booking.setRoom(room);
        }

        return ResponseEntity.ok(bookingRepository.save(booking));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Booking> update(@PathVariable Long id, @RequestBody Booking bookingData) {
        return bookingRepository.findById(id).map(booking -> {
            booking.setStartDate(bookingData.getStartDate());
            booking.setEndDate(bookingData.getEndDate());

            if (bookingData.getClient() != null) {
                Client client = clientRepository.findById(bookingData.getClient().getId()).orElse(null);
                booking.setClient(client);
            }

            if (bookingData.getRoom() != null) {
                Room room = roomRepository.findById(bookingData.getRoom().getId()).orElse(null);
                booking.setRoom(room);
            }

            return ResponseEntity.ok(bookingRepository.save(booking));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return bookingRepository.findById(id).map(b -> {
            bookingRepository.delete(b);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
