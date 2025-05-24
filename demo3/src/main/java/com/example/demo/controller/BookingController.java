package com.example.demo.controller;

import com.example.demo.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;

    @GetMapping("/api/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // Другие CRUD методы (get, create, delete)
}
