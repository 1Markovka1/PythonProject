package controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import dto.BookingRequest;
import dto.BookingUpdateRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.BookingService;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public String getAllBookings(Model model) {
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings/list";
    }

    @GetMapping("/new")
    public String showBookingForm(Model model) {
        model.addAttribute("bookingRequest", new BookingRequest());
        return "bookings/form";
    }

    @PostMapping
    public String createBooking(@ModelAttribute BookingRequest request,
                                @AuthenticationPrincipal UserDetails userDetails) {
        bookingService.createBooking(request, userDetails.getUsername());
        return "redirect:/bookings";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("booking", bookingService.getBookingById(id));
        model.addAttribute("updateRequest", new BookingUpdateRequest());
        return "bookings/edit";
    }

    @PostMapping("/{id}")
    public String updateBooking(@PathVariable Long id,
                                @ModelAttribute BookingUpdateRequest request) {
        bookingService.updateBooking(id, request);
        return "redirect:/bookings";
    }

    @PostMapping("/{id}/cancel")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/bookings";
    }
}
