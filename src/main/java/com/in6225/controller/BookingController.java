package com.in6225.controller;

import com.in6225.model.Booking;
import com.in6225.model.TourPackage;
import com.in6225.repository.BookingRepository;
import com.in6225.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.in6225.service.BookingPushService;
import com.in6225.dto.BookingDTO;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private TourPackageRepository tourPackageRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingPushService bookingPushService;

    // Step 1: Display the confirm booking page
    @GetMapping("/confirm/{id}")
    public String showConfirmBookingPage(@PathVariable("id") int tourId, Model model) {
        TourPackage tour = tourPackageRepository.findById(tourId);
        model.addAttribute("tour", tour);
        model.addAttribute("booking", new Booking());
        return "confirm-booking";
    }

    // Step 2: Save booking & show popup
    @PostMapping("/submit")
    public String handleBooking(@ModelAttribute Booking booking, Model model) {
        // Save to local DB
        bookingRepository.save(booking);

        // Push to microservice if it's an Australia tour (tour_id = 2)
        if (booking.getTourId() == 2) {
            BookingDTO dto = BookingDTO.builder()
                    .tourId(booking.getTourId())
                    .bookingDate(booking.getBookingDate().toLocalDate())
                    .firstName(booking.getFirstName())
                    .lastName(booking.getLastName())
                    .email(booking.getEmail())
                    .phone(booking.getPhone())
                    .build();

            bookingPushService.pushToAustraliaService(dto);
        }

        model.addAttribute("success", true); // Show popup
        TourPackage tour = tourPackageRepository.findById(booking.getTourId());
        model.addAttribute("tour", tour);
        return "confirm-booking";
    }

}
