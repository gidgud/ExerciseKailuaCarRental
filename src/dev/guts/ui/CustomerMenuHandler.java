package dev.guts.ui;

import dev.guts.db.DB;
import dev.guts.db.DbException;
import dev.guts.model.dao.RenterDAO;
import dev.guts.model.dao.impl.RenterDAOImpl;
import dev.guts.model.entities.Renter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CustomerMenuHandler {

    private RenterDAO renterDAO;
    private Scanner scanner;

    public CustomerMenuHandler() {
        this.renterDAO = new RenterDAOImpl(DB.getDataSource());
        this.scanner = new Scanner(System.in);
    }

    public void customerMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1: Create new customer");
            System.out.println("2: Update customer");
            System.out.println("3: Delete customer");
            System.out.println("0: Back to main menu");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        createCustomer();
                        break;
                    case 2:
                        updateCustomer();
                        break;
                    case 3:
                        deleteCustomer();
                        break;
                    case 0:
                        backToMain = true;
                        break;
                    default:
                        System.out.println("Invalid choice, please try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }

    private void createCustomer() {
        System.out.println("\n=== Create New Customer ===");

        try {
            System.out.print("Driver License Number: ");
            String licenseNumber = scanner.nextLine().trim();

            System.out.print("Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Address: ");
            String address = scanner.nextLine().trim();

            System.out.print("ZIP Code: ");
            String zip = scanner.nextLine().trim();

            System.out.print("Mobile Phone: ");
            String mobilePhone = scanner.nextLine().trim();

            System.out.print("Phone (optional): ");
            String phone = scanner.nextLine().trim();
            if (phone.isEmpty()) phone = null;

            System.out.print("Email: ");
            String email = scanner.nextLine().trim();

            System.out.print("Driver Since Date (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine().trim();
            LocalDate driverSinceDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);

            Renter renter = new Renter(licenseNumber, name, address, zip, mobilePhone, phone, email, driverSinceDate);

            Renter createdRenter = renterDAO.create(renter);
            System.out.println("Customer created successfully!");

        } catch (DbException e) {
            System.err.println("Error creating customer: " + e.getMessage());
        }
    }

    private void updateCustomer() {
        System.out.println("\n=== Update Customer ===");

        try {
            System.out.print("Enter Driver License Number to update: ");
            String licenseNumber = scanner.nextLine().trim();

            System.out.print("New Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("New Address: ");
            String address = scanner.nextLine().trim();

            System.out.print("New ZIP Code: ");
            String zip = scanner.nextLine().trim();

            System.out.print("New Mobile Phone: ");
            String mobilePhone = scanner.nextLine().trim();

            System.out.print("New Phone (optional): ");
            String phone = scanner.nextLine().trim();
            if (phone.isEmpty()) phone = null;

            System.out.print("New Email: ");
            String email = scanner.nextLine().trim();

            System.out.print("New Driver Since Date (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine().trim();
            LocalDate driverSinceDate = LocalDate.parse(dateInput, DateTimeFormatter.ISO_LOCAL_DATE);

            Renter renter = new Renter(licenseNumber, name, address, zip, mobilePhone, phone, email, driverSinceDate);

            Renter updatedRenter = renterDAO.update(renter);
            System.out.println("Customer updated successfully!");

        } catch (DbException e) {
            System.err.println("Error updating customer: " + e.getMessage());
        }
    }

    private void deleteCustomer() {
        System.out.println("\n=== Delete Customer ===");

        try {
            System.out.print("Enter Driver License Number to delete: ");
            String licenseNumber = scanner.nextLine().trim();

            System.out.print("Are you sure you want to delete customer " + licenseNumber + "? (y/n): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y")) {
                boolean deleted = renterDAO.delete(licenseNumber);

                if (deleted) {
                    System.out.println("Customer deleted successfully!");
                } else {
                    System.out.println("No customer found with license number: " + licenseNumber);
                }
            } else {
                System.out.println("Delete operation cancelled");
            }

        } catch (DbException e) {
            System.err.println("Error deleting customer: " + e.getMessage());
        }
    }
}