package dev.guts.model.dao;

import dev.guts.model.entities.RentalContract;

import java.util.List;

public interface RentalContractDAO {

    RentalContract create(RentalContract contract);
    RentalContract update(RentalContract contract);
    boolean delete(int contractId);
    RentalContract findById(int contractId);
    List<RentalContract> findAll();
    List<RentalContract> findByRenter(String licenseNumber);
}
