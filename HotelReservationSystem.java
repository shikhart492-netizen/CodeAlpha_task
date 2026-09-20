 import java.io.*;
import java.util.*;

class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean available;

    Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayRoom() {
        System.out.println(
            "Room: " + roomNumber +
            " | Category: " + category +
            " | Price: Rs." + price +
            " | " + (available ? "Available" : "Booked")
        );
    }
}

class Reservation {
    private int bookingId;
    private String customerName;
    private int roomNumber;
    private String category;
    private double amount;

    Reservation(int bookingId, String customerName, int roomNumber,
                String category, double amount) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.category = category;
        this.amount = amount;
    }

    public int getBookingId() {
        return bookingId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void displayBooking() {
        System.out.println("\n----- Booking Details -----");
        System.out.println("Booking ID : " + bookingId);
        System.out.println("Customer   : " + customerName);
        System.out.println("Room No.   : " + roomNumber);
        System.out.println("Category   : " + category);
        System.out.println("Amount     : Rs." + amount);
    }

    public String toFileString() {
        return bookingId + "," + customerName + "," +
               roomNumber + "," + category + "," + amount;
    }
}

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);
    static int nextBookingId = 1001;

    public static void main(String[] args) {

        addRooms();
        loadBookings();

        while (true) {
            System.out.println("\n==============================");
            System.out.println("   HOTEL RESERVATION SYSTEM");
            System.out.println("==============================");
            System.out.println("1. Search Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. View Booking Details");
            System.out.println("5. View All Rooms");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    searchRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    cancelReservation();
                    break;

                case 4:
                    viewBooking();
                    break;

                case 5:
                    viewAllRooms();
                    break;

                case 6:
                    System.out.println("Thank you for using Hotel Reservation System!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Add rooms
    static void addRooms() {

        rooms.add(new Room(101, "Standard", 1500));
        rooms.add(new Room(102, "Standard", 1500));
        rooms.add(new Room(201, "Deluxe", 2500));
        rooms.add(new Room(202, "Deluxe", 2500));
        rooms.add(new Room(301, "Suite", 4000));
        rooms.add(new Room(302, "Suite", 4000));
    }

    // Search rooms
    static void searchRooms() {

        sc.nextLine();

        System.out.print("Enter category (Standard/Deluxe/Suite): ");
        String category = sc.nextLine();

        boolean found = false;

        System.out.println("\nAvailable Rooms:");

        for (Room room : rooms) {

            if (room.getCategory().equalsIgnoreCase(category)
                    && room.isAvailable()) {

                room.displayRoom();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available in this category.");
        }
    }

    // Book room
    static void bookRoom() {

        sc.nextLine();

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter room number: ");
        int roomNumber = sc.nextInt();

        Room selectedRoom = null;

        for (Room room : rooms) {

            if (room.getRoomNumber() == roomNumber
                    && room.isAvailable()) {

                selectedRoom = room;
                break;
            }
        }

        if (selectedRoom == null) {
            System.out.println("Room not available or invalid room number.");
            return;
        }

        double amount = selectedRoom.getPrice();

        System.out.println("\nRoom Price: Rs." + amount);
        System.out.println("1. Pay Now");
        System.out.println("2. Cancel");

        System.out.print("Choose payment option: ");
        int payment = sc.nextInt();

        if (payment != 1) {
            System.out.println("Booking cancelled.");
            return;
        }

        // Payment simulation
        System.out.println("Processing payment...");
        System.out.println("Payment successful!");

        int bookingId = nextBookingId++;

        Reservation reservation = new Reservation(
                bookingId,
                name,
                selectedRoom.getRoomNumber(),
                selectedRoom.getCategory(),
                amount
        );

        reservations.add(reservation);

        selectedRoom.setAvailable(false);

        saveBookings();

        System.out.println("\nBooking successful!");
        System.out.println("Your Booking ID: " + bookingId);
    }

    // Cancel reservation
    static void cancelReservation() {

        System.out.print("Enter Booking ID: ");
        int id = sc.nextInt();

        Reservation found = null;

        for (Reservation reservation : reservations) {

            if (reservation.getBookingId() == id) {
                found = reservation;
                break;
            }
        }

        if (found == null) {
            System.out.println("Booking not found.");
            return;
        }

        for (Room room : rooms) {

            if (room.getRoomNumber() == found.getRoomNumber()) {
                room.setAvailable(true);
                break;
            }
        }

        reservations.remove(found);

        saveBookings();

        System.out.println("Reservation cancelled successfully.");
    }

    // View booking
    static void viewBooking() {

        System.out.print("Enter Booking ID: ");
        int id = sc.nextInt();

        for (Reservation reservation : reservations) {

            if (reservation.getBookingId() == id) {
                reservation.displayBooking();
                return;
            }
        }

        System.out.println("Booking not found.");
    }

    // View all rooms
    static void viewAllRooms() {

        System.out.println("\n----- All Rooms -----");

        for (Room room : rooms) {
            room.displayRoom();
        }
    }

    // Save bookings to file
    static void saveBookings() {

        try {
            FileWriter writer = new FileWriter("bookings.txt");

            for (Reservation reservation : reservations) {
                writer.write(reservation.toFileString() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving bookings.");
        }
    }

    // Load bookings from file
    static void loadBookings() {

        File file = new File("bookings.txt");

        if (!file.exists()) {
            return;
        }

        try {

            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();

                String[] data = line.split(",");

                if (data.length == 5) {

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int roomNumber = Integer.parseInt(data[2]);
                    String category = data[3];
                    double amount = Double.parseDouble(data[4]);

                    Reservation reservation =
                            new Reservation(
                                    id,
                                    name,
                                    roomNumber,
                                    category,
                                    amount
                            );

                    reservations.add(reservation);

                    if (id >= nextBookingId) {
                        nextBookingId = id + 1;
                    }

                    // Mark room as booked
                    for (Room room : rooms) {

                        if (room.getRoomNumber() == roomNumber) {
                            room.setAvailable(false);
                        }
                    }
                }
            }

            fileScanner.close();

        } catch (Exception e) {
            System.out.println("Error loading bookings.");
        }
    }
}
    

