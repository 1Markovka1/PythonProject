package service;

import model.Room;
import org.springframework.stereotype.Service;
import repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    // Внедрение зависимости через конструктор
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    // Получить все комнаты
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Получить комнату по ID
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    // Сохранить или обновить комнату
    public Room saveRoom(Room room) {
        return roomRepository.save(room);
    }

    // Удалить комнату
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    // Найти доступные комнаты
    public List<Room> getAvailableRooms() {
        return roomRepository.findByIsAvailableTrue();
    }

    // Дополнительные методы (например, поиск по типу комнаты)
    public List<Room> getRoomsByType(String roomType) {
        return roomRepository.findByRoomTypeAndIsAvailableTrue(roomType);
    }
}
