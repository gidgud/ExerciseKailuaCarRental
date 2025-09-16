package dev.guts.model.dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import dev.guts.db.DbException;
import dev.guts.model.dao.RenterDAO;
import dev.guts.model.entities.Renter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RenterDAOImpl implements RenterDAO {

    private final HikariDataSource dataSource;

    public RenterDAOImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Renter create(Renter renter) {
        String sql = "INSERT INTO renter(driver_license_number, name, address, zip, mobile_phone, phone, email, driver_since_date)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){



        } catch (SQLException e) {
            throw new DbException("Error creating new renter");
        }


    }

    @Override
    public Renter Update(Renter renter) {
        return null;
    }

    @Override
    public boolean delete(String driverLicenseNumber) {
        return false;
    }
}
