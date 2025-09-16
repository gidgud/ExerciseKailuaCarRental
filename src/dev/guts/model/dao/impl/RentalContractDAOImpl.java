package dev.guts.model.dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import dev.guts.db.DbException;
import dev.guts.model.dao.RentalContractDAO;
import dev.guts.model.entities.RentalContract;
import org.mariadb.jdbc.Statement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalContractDAOImpl implements RentalContractDAO {

    private final HikariDataSource dataSource;

    public RentalContractDAOImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public RentalContract create(RentalContract contract) {
        String sql = "INSERT INTO rental_contract(driver_license_number, from_date_time, to_date_time, " +
                "max_km, start_odometer, registration_number) VALUES(?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, contract.getDriverLicenseNumber());
            statement.setTimestamp(2, Timestamp.valueOf(contract.getFromDateTime()));
            statement.setTimestamp(3, Timestamp.valueOf(contract.getToDateTime()));
            statement.setInt(4, contract.getMaxKm());
            statement.setInt(5, contract.getStartOdometer());
            statement.setString(6, contract.getRegistrationNumber());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DbException("Failed to create rental contract - no rows affected");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    contract.setContractId(generatedKeys.getInt(1));
                } else {
                    throw new DbException("Failed to create rental contract");
                }
            }

            return contract;

        } catch (SQLException e) {
            throw new DbException("Error creating rental contract: " + e.getMessage());
        }
    }

    @Override
    public RentalContract update(RentalContract contract) {
        String sql = "UPDATE rental_contract SET driver_license_number = ?, from_date_time = ?, " +
                "to_date_time = ?, max_km = ?, start_odometer = ?, registration_number = ? " +
                "WHERE contract_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, contract.getDriverLicenseNumber());
            statement.setTimestamp(2, Timestamp.valueOf(contract.getFromDateTime()));
            statement.setTimestamp(3, Timestamp.valueOf(contract.getToDateTime()));
            statement.setInt(4, contract.getMaxKm());
            statement.setInt(5, contract.getStartOdometer());
            statement.setString(6, contract.getRegistrationNumber());
            statement.setInt(7, contract.getContractId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new DbException("Failed to update rental contract - contract not found with ID: " +
                        contract.getContractId());
            }

            return contract;

        } catch (SQLException e) {
            throw new DbException("Error updating rental contract: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(int contractId) {
        String sql = "DELETE FROM rental_contract WHERE contract_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, contractId);

            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            throw new DbException("Error deleting rental contract: " + e.getMessage());
        }
    }

    @Override
    public RentalContract findById(int contractId) {
        String sql = "SELECT contract_id, driver_license_number, from_date_time, to_date_time, " +
                "max_km, start_odometer, registration_number FROM rental_contract " +
                "WHERE contract_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, contractId);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToRentalContract(rs);
                }
                return null;
            }

        } catch (SQLException e) {
            throw new DbException("Error finding rental contract by ID: " + e.getMessage());
        }
    }

    @Override
    public List<RentalContract> findAll() {
        String sql = "SELECT contract_id, driver_license_number, from_date_time, to_date_time, " +
                "max_km, start_odometer, registration_number FROM rental_contract " +
                "ORDER BY contract_id";

        List<RentalContract> contracts = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                contracts.add(mapResultSetToRentalContract(rs));
            }

            return contracts;

        } catch (SQLException e) {
            throw new DbException("Error finding all rental contracts: " + e.getMessage());
        }
    }

    @Override
    public List<RentalContract> findByRenter(String licenseNumber) {
        String sql = "SELECT contract_id, driver_license_number, from_date_time, to_date_time, " +
                "max_km, start_odometer, registration_number FROM rental_contract " +
                "WHERE driver_license_number = ? ORDER BY from_date_time DESC";

        List<RentalContract> contracts = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, licenseNumber);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    contracts.add(mapResultSetToRentalContract(rs));
                }
            }

            return contracts;

        } catch (SQLException e) {
            throw new DbException("Error finding rental contracts by renter: " + e.getMessage());
        }
    }

    private RentalContract mapResultSetToRentalContract(ResultSet rs) throws SQLException {
        RentalContract contract = new RentalContract();
        contract.setContractId(rs.getInt("contract_id"));
        contract.setDriverLicenseNumber(rs.getString("driver_license_number"));
        contract.setFromDateTime(rs.getTimestamp("from_date_time").toLocalDateTime());
        contract.setToDateTime(rs.getTimestamp("to_date_time").toLocalDateTime());
        contract.setMaxKm(rs.getInt("max_km"));
        contract.setStartOdometer(rs.getInt("start_odometer"));
        contract.setRegistrationNumber(rs.getString("registration_number"));
        return contract;
    }
}
