package com.in6225.model;

import lombok.Data;

@Data
public class TourPackage {
    private int packageId;
    private String title;
    private String description;
    private String country;
    private String city;
    private String attraction;
    private double rating;
    private double price;
    private int durationDays;
    private String imageUrl;
    private String status;
}
