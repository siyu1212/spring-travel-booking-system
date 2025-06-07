package com.in6225.model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String username;
    private String password;
    private String role; // Either 'USER' or 'ADMIN'
}
