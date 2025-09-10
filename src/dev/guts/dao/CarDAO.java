package dev.guts.dao;

import dev.guts.model.Car;

import java.util.List;

public interface CarDAO {

    Car findById(String registrationNumber);
    List<Car> findAll();
}
