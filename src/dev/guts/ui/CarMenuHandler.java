package dev.guts.ui;

import dev.guts.db.DB;
import dev.guts.db.DbException;
import dev.guts.model.dao.CarDAO;
import dev.guts.model.dao.impl.CarDAOImpl;
import dev.guts.model.entities.Car;

import java.util.List;
import java.util.Scanner;

public class CarMenuHandler {

    private CarDAO carDAO;
    private Scanner scanner;

    public CarMenuHandler() {
        this.carDAO = new CarDAOImpl(DB.getDataSource());
        this.scanner = new Scanner(System.in);
    }

    public void carMenu() {

        boolean backToMain = false;

        while(!backToMain) {
            System.out.println("\n=== Car Menu ===");
            System.out.println("1: View all cars");
            System.out.println("2: Find car by registration");
            System.out.println("0: Back to main menu");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        showAllCars();
                        break;
                    case 2:
                        showCarByRegNumber();
                        break;
                    case 0:
                        backToMain = true;
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    public void showAllCars() {
        try {
            List<Car> cars = carDAO.showAll();
            System.out.println("All cars:");
            cars.forEach(car -> System.out.println(car.getRegistrationNumber() + " - " +
                    car.getBrand() + " " + car.getModel()));
        } catch (DbException e) {
            System.err.println("Error retrieving cars");
        }
    }

    public void showCarByRegNumber() {
        System.out.println("Please enter a valid registration number: ");
        try {
            String regNumber = scanner.nextLine();

            Car car = carDAO.findByRegNumber(regNumber);
            System.out.println(car);

        } catch (DbException e) {
            System.err.println("Couldn't find a car with that registration number");
        }
    }
}
