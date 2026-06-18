package org.zs.shardingsphere.algorithm;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class TableInitializer {
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void init() throws SQLException {
        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS user_2026 (id BIGINT PRIMARY KEY, username VARCHAR(100), create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP)");
            for (int year = 2023; year <= 2080; year++) {
                stmt.execute("CREATE TABLE IF NOT EXISTS user_" + year + " LIKE user_2026");
            }
        }
    }
}