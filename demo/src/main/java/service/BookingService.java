package service;

import dto.BookingRequest;
import dto.BookingUpdateRequest;
import jakarta.transaction.Transactional;
import model.*;
import org.springframework.stereotype.Service;
import repository.BookingRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    private final ClientService clientService;
    private final AdministratorService administratorService;

    public BookingService(BookingRepository bookingRepository,
                          RoomService roomService,
                          ClientService clientService,
                          AdministratorService administratorService) {
        this.bookingRepository = bookingRepository;
        this.roomService = roomService;
        this.clientService = clientService;
        this.administratorService = administratorService;
    }

    // Создание бронирования
    @Transactional
    public Booking createBooking(BookingRequest request, String adminUsername) {
        Room room = roomService.getRoomById(request.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Client client = clientService.getClientById(request.getClientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Administrator admin = administratorService.getAdministratorByUsername(adminUsername)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        // Проверка доступности комнаты
        if (!isRoomAvailable(room.getId(), request.getCheckInDate(), request.getCheckOutDate())) {
            throw new RuntimeException("Room is not available for selected dates");
        }

        // Расчет стоимости
        long days = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());
        BigDecimal totalPrice = room.getPricePerNight().multiply(BigDecimal.valueOf(days));

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setClient(client);
        booking.setAdmin(admin);
        booking.setCheckInDate(request.getCheckInDate());
        booking.setCheckOutDate(request.getCheckOutDate());
        booking.setTotalPrice(totalPrice);
        booking.setStatus(BookingStatus.CONFIRMED);
        booking.setNotes(request.getNotes());

        return bookingRepository.save(booking);
    }

    // Проверка доступности комнаты
    public boolean isRoomAvailable(Long roomId, LocalDate checkIn, LocalDate checkOut) {
        return bookingRepository.findOverlappingBookings(roomId, checkIn, checkOut, BookingStatus.CONFIRMED).isEmpty();
    }

    // Получение бронирования по ID
    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    // Получение всех бронирований
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Обновление бронирования
    @Transactional
    public Booking updateBooking(Long id, BookingUpdateRequest request) {
        Booking booking = getBookingById(id);

        if (request.getRoomId() != null) {
            Room room = roomService.getRoomById(request.getRoomId())
                    .orElseThrow(() -> new RuntimeException("Room not found with id: " + request.getRoomId()));
            booking.setRoom(room);
        }

        if (request.getCheckInDate() != null && request.getCheckOutDate() != null) {
            booking.setCheckInDate(request.getCheckInDate());
            booking.setCheckOutDate(request.getCheckOutDate());

            // Пересчет стоимости
            long days = ChronoUnit.DAYS.between(request.getCheckInDate(), request.getCheckOutDate());
            BigDecimal totalPrice = booking.getRoom().getPricePerNight().multiply(BigDecimal.valueOf(days));
            booking.setTotalPrice(totalPrice);
        }

        if (request.getStatus() != null) {
            booking.setStatus(request.getStatus());
        }

        if (request.getNotes() != null) {
            booking.setNotes(request.getNotes());
        }

        return bookingRepository.save(booking);
    }

    // Отмена бронирования
    @Transactional
    public Booking cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        booking.setStatus(BookingStatus.CANCELLED);
        return bookingRepository.save(booking);
    }

    // Получение бронирований по клиенту
    public List<Booking> getBookingsByClient(Long clientId) {
        Client client = clientService.getClientById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        return bookingRepository.findByClient(client);
    }

    // Получение бронирований по комнате
    public List<Booking> getBookingsByRoom(Long roomId) {
        return roomService.getRoomById(roomId)
                .map(bookingRepository::findByRoom)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }
}
