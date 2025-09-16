package dev.guts.ui;

import dev.guts.db.DB;
import dev.guts.db.DbException;
import dev.guts.model.dao.RentalContractDAO;
import dev.guts.model.dao.impl.RentalContractDAOImpl;
import dev.guts.model.entities.RentalContract;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class RentalMenuHandler {

    private RentalContractDAO rentalContractDAO;
    private Scanner scanner;

    public RentalMenuHandler() {
        this.rentalContractDAO = new RentalContractDAOImpl(DB.getDataSource());
        this.scanner = new Scanner(System.in);
    }

    public void rentalMenu() {
        boolean backToMain = false;

        while (!backToMain) {
            System.out.println("\n=== Rental Menu ===");
            System.out.println("1: Create new rental contract");
            System.out.println("2: Update rental contract");
            System.out.println("3: Delete rental contract");
            System.out.println("4: Find contract by ID");
            System.out.println("5: View all contracts");
            System.out.println("6: Find contracts by customer");
            System.out.println("0: Back to main menu");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        createRentalContract();
                        break;
                    case 2:
                        updateRentalContract();
                        break;
                    case 3:
                        deleteRentalContract();
                        break;
                    case 4:
                        findContractById();
                        break;
                    case 5:
                        viewAllContracts();
                        break;
                    case 6:
                        findContractsByCustomer();
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

    private void createRentalContract() {
        System.out.println("\n=== Create New Rental Contract ===");

        try {
            System.out.print("Driver License Number: ");
            String licenseNumber = scanner.nextLine().trim();

            System.out.print("Car Registration Number: ");
            String registrationNumber = scanner.nextLine().trim();

            System.out.print("From Date and Time (yyyy-MM-dd HH:mm): ");
            String fromDateTimeInput = scanner.nextLine().trim();
            LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeInput,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.print("To Date and Time (yyyy-MM-dd HH:mm): ");
            String toDateTimeInput = scanner.nextLine().trim();
            LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeInput,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.print("Maximum Kilometers: ");
            int maxKm = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Start Odometer Reading: ");
            int startOdometer = Integer.parseInt(scanner.nextLine().trim());

            RentalContract contract = new RentalContract();
            contract.setDriverLicenseNumber(licenseNumber);
            contract.setRegistrationNumber(registrationNumber);
            contract.setFromDateTime(fromDateTime);
            contract.setToDateTime(toDateTime);
            contract.setMaxKm(maxKm);
            contract.setStartOdometer(startOdometer);

            RentalContract createdContract = rentalContractDAO.create(contract);
            System.out.println("Rental contract created successfully!");
            System.out.println("Contract ID: " + createdContract.getContractId());

        } catch (DbException e) {
            System.err.println("Error creating rental contract: " + e.getMessage());
        }
    }

    private void updateRentalContract() {
        System.out.println("\n=== Update Rental Contract ===");

        try {
            System.out.print("Enter Contract ID to update: ");
            int contractId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Driver License Number: ");
            String licenseNumber = scanner.nextLine().trim();

            System.out.print("Car Registration Number: ");
            String registrationNumber = scanner.nextLine().trim();

            System.out.print("From Date and Time (yyyy-MM-dd HH:mm): ");
            String fromDateTimeInput = scanner.nextLine().trim();
            LocalDateTime fromDateTime = LocalDateTime.parse(fromDateTimeInput,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.print("To Date and Time (yyyy-MM-dd HH:mm): ");
            String toDateTimeInput = scanner.nextLine().trim();
            LocalDateTime toDateTime = LocalDateTime.parse(toDateTimeInput,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.print("Maximum Kilometers: ");
            int maxKm = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Start Odometer Reading: ");
            int startOdometer = Integer.parseInt(scanner.nextLine().trim());

            RentalContract contract = new RentalContract();
            contract.setContractId(contractId);
            contract.setDriverLicenseNumber(licenseNumber);
            contract.setRegistrationNumber(registrationNumber);
            contract.setFromDateTime(fromDateTime);
            contract.setToDateTime(toDateTime);
            contract.setMaxKm(maxKm);
            contract.setStartOdometer(startOdometer);

            RentalContract updatedContract = rentalContractDAO.update(contract);
            System.out.println("Rental contract updated successfully!");

        } catch (DateTimeParseException e) {
            System.err.println("Invalid date/time format. Please use yyyy-MM-dd HH:mm format");
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format");
        } catch (DbException e) {
            System.err.println("Error updating rental contract: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private void deleteRentalContract() {
        System.out.println("\n=== Delete Rental Contract ===");

        try {
            System.out.print("Enter Contract ID to delete: ");
            int contractId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Are you sure you want to delete contract " + contractId + "? (y/N): ");
            String confirmation = scanner.nextLine().trim().toLowerCase();

            if (confirmation.equals("y") || confirmation.equals("yes")) {
                boolean deleted = rentalContractDAO.delete(contractId);

                if (deleted) {
                    System.out.println("Rental contract deleted successfully!");
                } else {
                    System.out.println("No contract found with ID: " + contractId);
                }
            } else {
                System.out.println("Delete operation cancelled");
            }

        } catch (NumberFormatException e) {
            System.err.println("Invalid contract ID format");
        } catch (DbException e) {
            System.err.println("Error deleting rental contract: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private void findContractById() {
        System.out.println("\n=== Find Contract by ID ===");

        try {
            System.out.print("Enter Contract ID: ");
            int contractId = Integer.parseInt(scanner.nextLine().trim());

            RentalContract contract = rentalContractDAO.findById(contractId);

            if (contract != null) {
                displayContract(contract);
            } else {
                System.out.println("No contract found with ID: " + contractId);
            }

        } catch (DbException e) {
            System.err.println("Error finding rental contract: " + e.getMessage());
        }
    }

    private void viewAllContracts() {
        System.out.println("\n=== All Rental Contracts ===");

        try {
            List<RentalContract> contracts = rentalContractDAO.findAll();

            if (contracts.isEmpty()) {
                System.out.println("No rental contracts found");
            } else {
                System.out.println("Total contracts: " + contracts.size());
                for (RentalContract contract : contracts) {
                    System.out.println("ID: " + contract.getContractId() +
                            " | Customer: " + contract.getDriverLicenseNumber() +
                            " | Car: " + contract.getRegistrationNumber() +
                            " | From: " + contract.getFromDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                }
            }

        } catch (DbException e) {
            System.err.println("Error retrieving rental contracts: " + e.getMessage());
        }
    }

    private void findContractsByCustomer() {
        System.out.println("\n=== Find Contracts by Customer ===");

        try {
            System.out.print("Enter Driver License Number: ");
            String licenseNumber = scanner.nextLine().trim();

            List<RentalContract> contracts = rentalContractDAO.findByRenter(licenseNumber);

            if (contracts.isEmpty()) {
                System.out.println("No contracts found for customer: " + licenseNumber);
            } else {
                System.out.println("Contracts for customer " + licenseNumber + ":");
                for (RentalContract contract : contracts) {
                    displayContract(contract);
                    System.out.println("---");
                }
            }

        } catch (DbException e) {
            System.err.println("Error finding rental contracts: " + e.getMessage());
        }
    }

    private void displayContract(RentalContract contract) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Contract ID: " + contract.getContractId());
        System.out.println("Customer: " + contract.getDriverLicenseNumber());
        System.out.println("Car: " + contract.getRegistrationNumber());
        System.out.println("From: " + contract.getFromDateTime().format(formatter));
        System.out.println("To: " + contract.getToDateTime().format(formatter));
        System.out.println("Max KM: " + contract.getMaxKm());
        System.out.println("Start Odometer: " + contract.getStartOdometer());
    }
}