package com.in6225.repository;

import com.in6225.model.TourPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TourPackageRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Get all tours (no filter)
    public List<TourPackage> findAll() {
        String sql = "SELECT * FROM tour_package WHERE status='ACTIVE'";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TourPackage.class));
    }

    // Get single tour by ID
    public TourPackage findById(int packageId) {
        String sql = "SELECT * FROM tour_package WHERE package_id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TourPackage.class), packageId);
    }

    // (Optional) Search by destination, activity, etc. will come next
    public List<TourPackage> findFiltered(String city, String attraction) {
        StringBuilder sql = new StringBuilder("SELECT * FROM tour_package WHERE status = 'ACTIVE'");
        List<Object> params = new ArrayList<>();

        boolean hasCity = city != null && !city.isEmpty() && !city.equals("Select Location");
        boolean hasAttraction = attraction != null && !attraction.isEmpty() && !attraction.equals("Select Any");

        if (hasCity || hasAttraction) {
            sql.append(" AND (");
            boolean addedCondition = false;

            if (hasCity) {
                sql.append("city = ?");
                params.add(city);
                addedCondition = true;
            }

            if (hasAttraction) {
                if (addedCondition) sql.append(" OR ");
                sql.append("attraction = ?");
                params.add(attraction);
            }

            sql.append(")");
        }

        return jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(TourPackage.class), params.toArray());
    }



}
