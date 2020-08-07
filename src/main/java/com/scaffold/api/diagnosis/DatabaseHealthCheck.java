package com.scaffold.api.diagnosis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DatabaseHealthCheck {

    private static final String VALIDATION_QUERY = "SELECT 1";
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DatabaseHealthCheck(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isHealthy() {
        try {
            this.jdbcTemplate.query(VALIDATION_QUERY, new SingleColumnRowMapper<>());
            return true;
        } catch (DataAccessException ex) {
            log.error("DataSource health check failed: {}", ex.toString());
            return false;
        }
    }
}
