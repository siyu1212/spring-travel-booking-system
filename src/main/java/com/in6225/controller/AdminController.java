package com.in6225.controller;

import com.in6225.model.Contact;
import com.in6225.repository.BookingRepository;
import com.in6225.repository.ContactRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private ContactRepository contactRepository;

    // ✅ Admin Messages Page
    @GetMapping("/admin/messages")
    public String viewMessages(HttpSession session, Model model) {
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equalsIgnoreCase(role)) {
            return "redirect:/login";
        }

        List<Contact> messages = contactRepository.getAllMessages();
        model.addAttribute("messages", messages);

        return "admin-messages";
    }

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/admin/dashboard")
    public String dashboard(Model model, HttpSession session) {
        String role = (String) session.getAttribute("role");
        if (!"ADMIN".equalsIgnoreCase(role)) {
            return "redirect:/login";
        }

        model.addAttribute("totalBookings", bookingRepository.countTotalBookings());
        model.addAttribute("totalRevenue", bookingRepository.getTotalRevenue());
        model.addAttribute("topDestination", bookingRepository.getTopDestination());
        model.addAttribute("topBookings", bookingRepository.getTop5Bookings());

        return "admin-dashboard";
    }
}
