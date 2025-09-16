package dev.guts.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {

    private static HikariDataSource dataSource;

    static {
        try {
            Properties props = loadProperties();
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(props.getProperty("dburl"));
            config.setUsername(System.getenv("MARIADB_USER"));
            config.setPassword(System.getenv("MARIADB_PASS"));

            dataSource = new HikariDataSource(config);
        } catch (Exception e) {
            throw new DbException("Failed to initialize connection pool");
        }
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }

    private static Properties loadProperties() {
        try (FileInputStream fs = new FileInputStream("kailua_car_rental.properties")) {
            Properties props = new Properties();
            props.load(fs);
            return props;
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        }
    }
}
