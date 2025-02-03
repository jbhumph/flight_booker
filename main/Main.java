package main;

import java.util.Scanner;
import plane.*;

// create main java class and main method
public class Main {
    public static void main(String[] args) {
        // create terminal to choose flight = MAIN
        // create new plane array with seat objects and randomly assigned passengers = PLANE
        // display available seats on flight = MAIN / AVAILABLE
        // user selects seat - print back confirmation = MAIN
        // choose to buy priority boarding = PLANE function
        // create priority queue for boarding and print out order = PQUEUE
        // run simulation of boarding = BOARDING

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Airport!");
        System.out.println("Please select a flight to board:");
        System.out.println("1. Flight to New York");
        int flight = scanner.nextInt();

        System.out.println("You have selected Flight to New York");
        System.out.println("Here are the available seats:");
        Plane plane = new Plane();
        plane.populateSeats();
        plane.displaySeats();

        boolean valid = false;
        char row = 0;
        int seat = 0;
        while (!valid) {
            System.out.println("Please select a seat:");
            System.out.println("Enter row (A-T):");
            row = scanner.next().charAt(0);
            row = Character.toUpperCase(row);
            System.out.println("Enter seat (1-4):");
            seat = scanner.nextInt();
            valid = plane.valid(row, seat);
        }

        System.out.println("Would you like to purchase priority boarding for $50? (Y/N)");
        char priority = scanner.next().charAt(0);
        if (priority == 'Y' || priority == 'y') {
            plane.buyPriority(row - 65, seat - 1);
        }

        System.out.println("Seat " + row + seat + " in " + plane.getpClass(row - 65, seat - 1) + " class to NEW YORK " + plane.getPriority() + "? (Y/N)");
        char confirm = scanner.next().charAt(0);
        if (confirm == 'Y' || confirm == 'y') {
            int id = plane.setPassenger(row, seat);
            System.out.println("Thank you for your purchase! Your passenger ID is " + id);
        } else {
            System.out.println("Please select another seat.");
        }

        plane.boardingOrder();

        plane.displayBoardingOrder();
    }


        
}