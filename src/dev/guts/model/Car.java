package dev.guts.model;

public class Car {
    private String registrationNumber;
    private String brand;
    private String model;
    private String fuelType;
    private int firstRegYear;
    private int firstRegMonth;
    private int odometer;
    private String groupName;

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
}
