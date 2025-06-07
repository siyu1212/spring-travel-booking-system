package com.in6225.service;

import com.in6225.repository.UserRepository;
import com.in6225.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public boolean login(String username, String password, HttpSession session) {
        try {
            User user = userRepository.findByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                // store info in session
                session.setAttribute("username", user.getUsername());
                session.setAttribute("role", user.getRole());
                return true;
            }
        } catch (Exception e) {
            // user not found or db error
            return false;
        }
        return false;
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }
}
