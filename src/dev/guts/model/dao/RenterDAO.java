package dev.guts.model.dao;

import dev.guts.model.entities.Renter;

public interface RenterDAO {

    Renter create(Renter renter);
    Renter update(Renter renter);
    boolean delete(String driverLicenseNumber);
}
