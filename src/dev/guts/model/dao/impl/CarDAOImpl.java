package dev.guts.model.dao.impl;

import com.zaxxer.hikari.HikariDataSource;
import dev.guts.model.dao.CarDAO;
import dev.guts.model.entities.Car;
import dev.guts.db.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {

    private final HikariDataSource dataSource;

    public CarDAOImpl(HikariDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Car findByRegNumber(String registrationNumber) {
        String sql = "SELECT * FROM car WHERE registration_number = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, registrationNumber);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToCar(rs);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new DbException("Error finding car by ID: " + registrationNumber);
        }
    }

    @Override
    public List<Car> showAll() {
        String sql = "SELECT * FROM car";
        List<Car> cars = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                cars.add(mapResultSetToCar(rs));
            }
            return cars;

        } catch (SQLException e) {
            throw new DbException("Error retrieving cars");
        }
    }

    private Car mapResultSetToCar(ResultSet rs) throws SQLException {
        Car car = new Car();
        car.setRegistrationNumber(rs.getString("registration_number"));
        car.setBrand(rs.getString("brand"));
        car.setModel(rs.getString("model"));
        car.setFuelType(rs.getString("fuel_type"));
        car.setFirstRegYear(rs.getInt("first_reg_year"));
        car.setFirstRegMonth(rs.getInt("first_reg_month"));
        car.setOdometer(rs.getInt("odometer"));
        car.setGroupName(rs.getString("group_name"));
        return car;
    }
}
