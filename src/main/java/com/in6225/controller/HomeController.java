package com.in6225.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

//    @GetMapping("/contact")
//    public String contact() {
//        return "contact";
//    }

    @GetMapping("/tours")
    public String tours() {
        return "tours";
    }
}