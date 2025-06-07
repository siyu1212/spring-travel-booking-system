package com.in6225.repository;

import com.in6225.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BookingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(Booking booking) {
        String sql = "INSERT INTO booking (tour_id, booking_date, first_name, last_name, email, phone) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, booking.getTourId(), booking.getBookingDate(), booking.getFirstName(),
                booking.getLastName(), booking.getEmail(), booking.getPhone());
    }
    // Count total bookings
    public int countTotalBookings() {
        String sql = "SELECT COUNT(*) FROM booking";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    // Get total sales revenue (sum of tour prices joined with booking)
    public double getTotalRevenue() {
        String sql = "SELECT SUM(tp.price) FROM booking b JOIN tour_package tp ON b.tour_id = tp.package_id";
        Double result = jdbcTemplate.queryForObject(sql, Double.class);
        return result != null ? result : 0.0;
    }

    // Get most popular destination (by country)
    public String getTopDestination() {
        String sql = """
        SELECT tp.country
        FROM booking b
        JOIN tour_package tp ON b.tour_id = tp.package_id
        GROUP BY tp.country
        ORDER BY COUNT(*) DESC
        LIMIT 1
    """;
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    // Get latest top 5 bookings
    public List<Map<String, Object>> getTop5Bookings() {
        String sql = """
        SELECT b.booking_id, b.booking_date, b.first_name, b.last_name, tp.title AS tour_title
        FROM booking b
        JOIN tour_package tp ON b.tour_id = tp.package_id
        ORDER BY b.created_at DESC
        LIMIT 5
    """;
        return jdbcTemplate.queryForList(sql);
    }

}
