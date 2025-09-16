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

            statement.setString(1, renter.getDriverLicenseNumber());
            statement.setString(2, renter.getName());
            statement.setString(3, renter.getAddress());
            statement.setString(4, renter.getZip());
            statement.setString(5, renter.getMobilePhone());
            statement.setString(6, renter.getPhone());
            statement.setString(7, renter.getEmail());
            statement.setDate(8, java.sql.Date.valueOf(renter.getDriverSinceDate()));

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DbException("Failed to create renter" +
                        renter.getDriverLicenseNumber());
            }

            return renter;

        } catch (SQLException e) {
            throw new DbException("Error creating new renter" + e.getMessage());
        }


    }

    @Override
    public Renter update(Renter renter) {
        String sql = "UPDATE renter SET name = ?, address = ?, zip = ?, mobile_phone = ?, " +
                "phone = ?, email = ?, driver_since_date = ? " +
                "WHERE driver_license_number = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, renter.getName());
            statement.setString(2, renter.getAddress());
            statement.setString(3, renter.getZip());
            statement.setString(4, renter.getMobilePhone());
            statement.setString(5, renter.getPhone());
            statement.setString(6, renter.getEmail());
            statement.setDate(7, java.sql.Date.valueOf(renter.getDriverSinceDate()));

            statement.setString(8, renter.getDriverLicenseNumber());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DbException("Failed to update renter, renter not found with license: " +
                        renter.getDriverLicenseNumber());
            }

            return renter;

        } catch (SQLException e) {
            throw new DbException("Error updating renter: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(String driverLicenseNumber) {
        String sql = "DELETE FROM renter WHERE driver_license_number = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, driverLicenseNumber);

            int affectedRows = statement.executeUpdate();

            return affectedRows > 0;

        } catch (SQLException e) {
            throw new DbException("Error deleting renter: " + e.getMessage());
        }
    }
}
