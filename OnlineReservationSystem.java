import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class OnlineReservationSystem {
    private static Map<String, String> users = new HashMap<>();
    private static Map<String, Reservation> reservations = new HashMap<>();
    private static int reservationCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Add some dummy users (you can customize this)
        users.put("admin", "password");
        users.put("user1", "12345");

        String username;
        String password;

        System.out.println("=== Online Reservation System ===");

        while (true) {
            System.out.print("Enter your username: ");
            username = scanner.nextLine();

            System.out.print("Enter your password: ");
            password = scanner.nextLine();

            if (login(username, password)) {
                System.out.println("Login successful!");
                break;
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }

        int choice;

        while (true) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    makeReservation(scanner);
                    break;
                case 2:
                    cancelReservation(scanner);
                    break;
                case 0:
                    System.out.println("Thank you for using the Online Reservation System!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static boolean login(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }

    private static void makeReservation(Scanner scanner) {
        System.out.println("\n=== Make Reservation ===");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();

        System.out.print("Enter class type: ");
        String classType = scanner.nextLine();

        System.out.print("Enter date of journey: ");
        String journeyDate = scanner.nextLine();

        System.out.print("Enter departure: ");
        String departure = scanner.nextLine();

        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();

        Reservation reservation = new Reservation(name, trainNumber, classType, journeyDate, departure, destination);
        String reservationId = "R" + reservationCounter++;
        reservations.put(reservationId, reservation);

        System.out.println("Reservation successfully made!");
        System.out.println("Your reservation ID is: " + reservationId);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.println("\n=== Cancel Reservation ===");

        System.out.print("Enter your reservation ID: ");
        String reservationId = scanner.nextLine();

        if (reservations.containsKey(reservationId)) {
            Reservation reservation = reservations.get(reservationId);
            System.out.println("Reservation details:");
            System.out.println(reservation.toString());

            System.out.print("Are you sure you want to cancel? (yes/no): ");
            String confirmation = scanner.nextLine();

            if (confirmation.equalsIgnoreCase("yes")) {
                reservations.remove(reservationId);
                System.out.println("Reservation successfully cancelled!");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("Invalid reservation ID. Please try again.");
        }
    }
}

class Reservation {
    private String name;
    private String trainNumber;
    private String classType;
    private String journeyDate;
    private String departure;
    private String destination;

    public Reservation(String name, String trainNumber, String classType,
            String journeyDate, String departure, String destination) {
        this.name = name;
        this.trainNumber = trainNumber;
        this.classType = classType;
        this.journeyDate = journeyDate;
        this.departure = departure;
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Name: " + name +
                "\nTrain Number: " + trainNumber +
                "\nClass Type: " + classType +
                "\nJourney Date: " + journeyDate +
                "\nDeparture: " + departure +
                "\nDestination: " + destination;
    }
}
