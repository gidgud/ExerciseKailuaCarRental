package dev.guts.model.entities;

import java.util.Date;

public class Renter {

    private String driverLicenseNumber;
    private String name;
    private String address;
    private String zip;
    private String mobilePhone;
    private String phone;
    private String email;
    private Date driverSinceDate;

    public Renter(String driverLicenseNumber, String name, String address, String zip, String mobilePhone, String phone, String email, Date driverSinceDate) {
        this.driverLicenseNumber = driverLicenseNumber;
        this.name = name;
        this.address = address;
        this.zip = zip;
        this.mobilePhone = mobilePhone;
        this.phone = phone;
        this.email = email;
        this.driverSinceDate = driverSinceDate;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDriverSinceDate() {
        return driverSinceDate;
    }

    public void setDriverSinceDate(Date driverSinceDate) {
        this.driverSinceDate = driverSinceDate;
    }
}
