package com.in6225.repository;

import com.in6225.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveContactMsg(Contact contact){
        String sql = "INSERT INTO contact_msg (name, mobile_num, email, subject, message, status, created_at, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                contact.getName(),
                contact.getMobileNum(),
                contact.getEmail(),
                contact.getSubject(),
                contact.getMessage(),
                contact.getStatus(),
                contact.getCreatedAt(),
                contact.getCreatedBy());
    }

    public List<Contact> getAllMessages() {
        String sql = "SELECT * FROM contact_msg";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Contact.class));
    }
}
