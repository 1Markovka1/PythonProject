package controller;

import org.springframework.ui.Model;
import model.Administrator;
import model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.AdministratorService;
import service.BookingService;
import service.RoomService;
import service.ClientService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final BookingService bookingService;
    private final AdministratorService adminService;
    private final ClientService clientService;
    private final RoomService roomService;

    @Autowired
    public AdminController(BookingService bookingService, AdministratorService adminService, ClientService clientService, RoomService roomService) {
        this.bookingService = bookingService;
        this.adminService = adminService;
        this.clientService = clientService;
        this.roomService = roomService;
    }

    @GetMapping("/bookings")
    public String getAllBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "admin/bookings";
    }

    @GetMapping("/bookings/new")
    public String showNewBookingForm(Model model) {
        model.addAttribute("booking", new Booking());
        model.addAttribute("rooms", roomService.getAvailableRooms());
        model.addAttribute("clients", clientService.getAllClients());
        return "admin/new-booking";
    }

    @PostMapping("/bookings")
    public String createBooking(@ModelAttribute("booking") Booking booking,
                                @RequestParam("roomId") Long roomId,
                                @RequestParam("clientId") Long clientId,
                                Principal principal) {
        String username = principal.getName();
        Administrator admin = adminService.getAdministratorByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));

        bookingService.createBooking(booking, roomId, clientId, admin);
        return "redirect:/admin/bookings";
    }

    @GetMapping("/bookings/edit/{id}")
    public String showEditBookingForm(@PathVariable Long id, Model model) {
        Booking booking = bookingService.getBookingById(id);
        model.addAttribute("booking", booking);
        model.addAttribute("rooms", roomService.getAllRooms());
        model.addAttribute("clients", clientService.getAllClients());
        return "admin/edit-booking";
    }

    @PostMapping("/bookings/{id}")
    public String updateBooking(@PathVariable Long id,
                                @ModelAttribute("booking") Booking bookingDetails) {
        bookingService.updateBooking(id, bookingDetails);
        return "redirect:/admin/bookings";
    }

    @PostMapping("/bookings/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/admin/bookings";
    }
}
