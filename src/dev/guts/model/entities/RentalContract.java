package dev.guts.model.entities;


import java.time.LocalDateTime;
import java.util.Date;

public class RentalContract {

    private int contractId;
    private String driverLicenseNumber;
    private LocalDateTime fromDateTime;
    private LocalDateTime toDateTime;
    private int maxKm;
    private int startOdometer;
    private String registrationNumber;

    public RentalContract() {

    }

    public RentalContract(int contractId, String driverLicenseNumber, LocalDateTime fromDateTime, LocalDateTime toDateTime, int maxKm, int startOdometer, String registrationNumber) {
        this.contractId = contractId;
        this.driverLicenseNumber = driverLicenseNumber;
        this.fromDateTime = fromDateTime;
        this.toDateTime = toDateTime;
        this.maxKm = maxKm;
        this.startOdometer = startOdometer;
        this.registrationNumber = registrationNumber;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public LocalDateTime getFromDateTime() {
        return fromDateTime;
    }

    public void setFromDateTime(LocalDateTime fromDateTime) {
        this.fromDateTime = fromDateTime;
    }

    public LocalDateTime getToDateTime() {
        return toDateTime;
    }

    public void setToDateTime(LocalDateTime toDateTime) {
        this.toDateTime = toDateTime;
    }

    public int getMaxKm() {
        return maxKm;
    }

    public void setMaxKm(int maxKm) {
        this.maxKm = maxKm;
    }

    public int getStartOdometer() {
        return startOdometer;
    }

    public void setStartOdometer(int startOdometer) {
        this.startOdometer = startOdometer;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
