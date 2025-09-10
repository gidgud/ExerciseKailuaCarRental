package dev.guts.dao;

import dev.guts.model.Car;

import java.sql.PreparedStatement;
import java.util.List;

public class CarDAOImpl implements CarDAO {

    @Override
    public Car findById(String registrationNumber) {
        String sql = "SELECT * FROM car WHERE registration_number = ?";
    }

    @Override
    public List<Car> findAll() {
        String sql = "SELECT * FROM car";
    }
}
