package controller;

import model.Room;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import service.RoomService;

import java.util.List;

@Controller
public class RoomController {

    private final RoomService roomService;

    // Внедрение зависимости через конструктор
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // Пример метода для отображения списка комнат
    @GetMapping("/rooms")
    public String getAllRooms(Model model) {
        List<Room> rooms = roomService.getAllRooms();
        model.addAttribute("rooms", rooms);  // Теперь model.addAttribute будет работать
        return "rooms";  // Имя Thymeleaf-шаблона (rooms.html)
    }
}
