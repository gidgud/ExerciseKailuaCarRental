package dev.guts.model.dao;

import dev.guts.model.entities.Car;

import java.util.List;

public interface CarDAO {

    Car findByRegNumber(String registrationNumber);
    List<Car> showAll();
}
