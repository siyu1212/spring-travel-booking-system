package com.in6225.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDTO
{
    private int tourId;
    private LocalDate bookingDate;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
