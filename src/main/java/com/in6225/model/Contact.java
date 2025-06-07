package com.in6225.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Contact {

    private int contactId;
    private String name;
    private String mobileNum;
    private String email;
    private String subject;
    private String message;
    private String status;
    private Timestamp createdAt;
    private String createdBy;
    private Timestamp updatedAt;
    private String updatedBy;
}
