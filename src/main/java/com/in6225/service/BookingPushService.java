package com.in6225.service;

import com.in6225.dto.BookingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BookingPushService {

    @Autowired
    private RestTemplate restTemplate;

    private final String AUSTRALIA_SERVICE_URL = "http://localhost:8081/bookings";

    public void pushToAustraliaService(BookingDTO bookingDTO) {
        restTemplate.postForObject("http://localhost:8081/bookings", bookingDTO, String.class);

    }
}
