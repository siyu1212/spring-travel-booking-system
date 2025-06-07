package com.in6225.controller;

import com.in6225.model.Contact;
import com.in6225.repository.ContactRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@Controller
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contact")
    public String showContactForm() {
        return "contact"; // display contact.html
    }

    @PostMapping("/contact")
    public String submitContactForm(@RequestParam("w3lName") String name,
                                    @RequestParam("w3lPhone") String mobileNum,
                                    @RequestParam("w3lSender") String email,
                                    @RequestParam("w3lSubject") String subject,
                                    @RequestParam("w3lMessage") String message,
                                    HttpSession session,
                                    Model model) {

        // ✅ Validation
        if (name.length() > 100) {
            model.addAttribute("error", "Name cannot exceed 100 characters.");
            return "contact";
        }
        if (mobileNum.length() > 10) {
            model.addAttribute("error", "Mobile number cannot exceed 10 digits.");
            return "contact";
        }
        if (email.length() > 100) {
            model.addAttribute("error", "Email cannot exceed 100 characters.");
            return "contact";
        }
        if (subject.length() > 100) {
            model.addAttribute("error", "Subject cannot exceed 100 characters.");
            return "contact";
        }
        if (message.length() > 500) {
            model.addAttribute("error", "Message cannot exceed 500 characters.");
            return "contact";
        }

        // ✅ Prepare contact object
        Contact contact = new Contact();
        contact.setName(name);
        contact.setMobileNum(mobileNum);
        contact.setEmail(email);
        contact.setSubject(subject);
        contact.setMessage(message);
        contact.setStatus("NEW");
        contact.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        // ✅ Set created_by as logged-in user or Anonymous
        Object username = session.getAttribute("username");
        contact.setCreatedBy(username != null ? username.toString() : "Anonymous");

        // ✅ Error handling for DB insert
        try {
            contactRepository.saveContactMsg(contact);
            model.addAttribute("success", "Message sent successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "Failed to send message. Please try again.");
        }

        return "contact"; // return back to contact.html
    }

}
