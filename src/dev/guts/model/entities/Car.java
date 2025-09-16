package dev.guts.model.entities;

public class Car {
    private String registrationNumber;
    private String brand;
    private String model;
    private String fuelType;
    private int firstRegYear;
    private int firstRegMonth;
    private int odometer;
    private String groupName;

    public Car() {
    }

    public Car(String registrationNumber, String brand, String model, String fuelType, int firstRegYear, int firstRegMonth, int odometer, String groupName) {
        this.registrationNumber = registrationNumber;
        this.brand = brand;
        this.model = model;
        this.fuelType = fuelType;
        this.firstRegYear = firstRegYear;
        this.firstRegMonth = firstRegMonth;
        this.odometer = odometer;
        this.groupName = groupName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public int getFirstRegYear() {
        return firstRegYear;
    }

    public int getFirstRegMonth() {
        return firstRegMonth;
    }

    public int getOdometer() {
        return odometer;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public void setFirstRegYear(int firstRegYear) {
        this.firstRegYear = firstRegYear;
    }

    public void setFirstRegMonth(int firstRegMonth) {
        this.firstRegMonth = firstRegMonth;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Registration Number: ").append(registrationNumber).append("\n");
        sb.append("Brand: ").append(brand).append("\n");
        sb.append("Model: ").append(model).append("\n");
        sb.append("Fuel Type: ").append(fuelType).append("\n");
        sb.append("First Registration Year: ").append(firstRegYear).append("\n");
        sb.append("First Registration Month: ").append(firstRegMonth).append("\n");
        sb.append("Odometer: ").append(odometer).append("\n");
        sb.append("Group name: ").append(groupName).append("\n");
        sb.append("");

        return sb.toString();
    }
}
