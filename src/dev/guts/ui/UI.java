package dev.guts.ui;

import dev.guts.db.DB;
import dev.guts.db.DbException;
import dev.guts.model.dao.CarDAO;
import dev.guts.model.dao.impl.CarDAOImpl;
import dev.guts.model.entities.Car;

import java.util.List;
import java.util.Scanner;

public class UI {

    private Scanner scanner;
    private CarMenuHandler carMenuHandler;


    public UI() {
        this.scanner = new Scanner(System.in);
        this.carMenuHandler = new CarMenuHandler();
    }

    public void start() {
        menu();
    }

    public void menu() {

        boolean running = true;

        while(running) {
            System.out.println("""
                    === Welcome to Kailua Car Rental Services ===
                    1: Car Menu
                    2: Customer Menu
                    3: Rental Menu
                    0: Exit
                    Enter your choice""");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        carMenuHandler.carMenu();
                        break;
                    case 2:
                        customerMenu();
                        break;
                    case 3:
                        rentalMenu();
                        break;
                    case 0:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid input, please try again.\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }



    public void customerMenu() {

    }

    public void rentalMenu() {

    }
}
