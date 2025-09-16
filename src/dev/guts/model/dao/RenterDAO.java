package dev.guts.model.dao;

import dev.guts.model.entities.Renter;

public interface RenterDAO {

    Renter create(Renter renter);
    Renter Update(Renter renter);
    boolean delete(String driverLicenseNumber);
}
