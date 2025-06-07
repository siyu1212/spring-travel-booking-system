package com.in6225.model;

import lombok.Data;
import java.sql.Date;

@Data
public class Booking {
    private int bookingId; // auto_increment so no need to insert
    private int tourId;
    private Date bookingDate;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
