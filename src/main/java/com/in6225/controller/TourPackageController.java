package com.in6225.controller;

import com.in6225.model.TourPackage;
import com.in6225.repository.TourPackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TourPackageController {

    @Autowired
    private TourPackageRepository tourPackageRepository;

    // Display all tours for selection page
//    @GetMapping("/tours/selection")
//    public String showTourSelection(Model model) {
//        List<TourPackage> tours = tourPackageRepository.findAll();
//        model.addAttribute("tours", tours);
//        return "tours-selection"; // this is the HTML you already created
//    }

    // Load all tours or filter based on user selection
    @GetMapping("/tours/selection")
    public String showTours(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String attraction,
            Model model) {

        List<TourPackage> tours;

        if ((city != null && !city.isEmpty() && !city.equals("Select Location")) ||
                (attraction != null && !attraction.isEmpty() && !attraction.equals("Select Any"))) {
            tours = tourPackageRepository.findFiltered(city, attraction);
        } else {
            tours = tourPackageRepository.findAll(); // load all if no filter
        }

        model.addAttribute("tours", tours);
        return "tours-selection";
    }

    // Display single tour detail
    @GetMapping("/tours/detail/{id}")
    public String showTourDetail(@PathVariable("id") int id, Model model) {
        TourPackage tour = tourPackageRepository.findById(id);
        model.addAttribute("tour", tour);
        return "tours-detail"; // this will be the next page we create
    }
}
