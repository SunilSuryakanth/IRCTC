package ticket.booking;

import ticket.booking.entities.Train;
import ticket.booking.entities.User;
import ticket.booking.services.UserBookingService;
import ticket.booking.util.UserServiceUtil;

import java.io.IOException;
import java.util.*;

public class App {

    public static void main(String[] args) {

        System.out.println("Running Train Booking System");

        Scanner sc = new Scanner(System.in);
        int option = 0;

        UserBookingService userBookingService;
        Train trainSelectedForBooking = null;

        try {
            userBookingService = new UserBookingService();
        } catch (IOException e) {
            System.out.println("Unable to initialize application.");
            return;
        }

        while (option != 7) {

            System.out.println("\nChoose an option");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Booking");
            System.out.println("4. Search Trains");
            System.out.println("5. Book Seat");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Exit");

            option = sc.nextInt();

            switch (option) {

                case 1: {
                    System.out.println("Enter username");
                    String username = sc.next();

                    System.out.println("Enter password");
                    String password = sc.next();

                    User user = new User(
                            username,
                            password,
                            UserServiceUtil.hashPassword(password),
                            new ArrayList<>(),
                            UUID.randomUUID().toString()
                    );

                    userBookingService.signUp(user);
                    break;
                }

                case 2: {
                    System.out.println("Enter username");
                    String username = sc.next();

                    System.out.println("Enter password");
                    String password = sc.next();

                    User user = new User(
                            username,
                            password,
                            UserServiceUtil.hashPassword(password),
                            new ArrayList<>(),
                            UUID.randomUUID().toString()
                    );

                    try {
                        userBookingService = new UserBookingService(user);
                        System.out.println("Login successful");
                    } catch (IOException e) {
                        System.out.println("Login failed");
                    }

                    break;
                }

                case 3: {
                    System.out.println("Fetching bookings...");
                    userBookingService.fetchBooking();
                    break;
                }

                case 4: {

                    System.out.println("Enter source station");
                    String source = sc.next();

                    System.out.println("Enter destination station");
                    String destination = sc.next();

                    List<Train> trains =
                            userBookingService.getTrains(source, destination);

                    if (trains.isEmpty()) {
                        System.out.println("No trains found");
                        break;
                    }

                    int index = 1;

                    for (Train train : trains) {

                        System.out.println(
                                index + ". Train ID : "
                                        + train.getTrainId()
                        );

                        for (Map.Entry<String, String> entry :
                                train.getStationTime().entrySet()) {

                            System.out.println(
                                    "Station : "
                                            + entry.getKey()
                                            + " Time : "
                                            + entry.getValue()
                            );
                        }

                        index++;
                        System.out.println();
                    }

                    System.out.println(
                            "Select train number (1,2,3...)"
                    );

                    int choice = sc.nextInt();

                    if (choice >= 1 && choice <= trains.size()) {
                        trainSelectedForBooking =
                                trains.get(choice - 1);
                    } else {
                        System.out.println("Invalid train selection");
                    }

                    break;
                }

                case 5: {

                    if (trainSelectedForBooking == null) {
                        System.out.println(
                                "Please search and select a train first."
                        );
                        break;
                    }

                    List<List<Integer>> seats =
                            userBookingService.fetchSeats(
                                    trainSelectedForBooking
                            );

                    System.out.println("Available seats:");

                    for (List<Integer> seatRow : seats) {
                        for (Integer seat : seatRow) {
                            System.out.print(seat + " ");
                        }
                        System.out.println();
                    }

                    System.out.println("Enter row");
                    int row = sc.nextInt();

                    System.out.println("Enter column");
                    int column = sc.nextInt();

                    Boolean booked =
                            userBookingService.bookTrainSeat(
                                    trainSelectedForBooking,
                                    row,
                                    column
                            );

                    if (Boolean.TRUE.equals(booked)) {
                        System.out.println(
                                "Seat booked successfully."
                        );
                    } else {
                        System.out.println(
                                "Unable to book seat."
                        );
                    }

                    break;
                }

                case 6: {

                    System.out.println("Cancelling booking...");

                    boolean cancelled =
                            userBookingService.cancelBooking();

                    if (cancelled) {
                        System.out.println(
                                "Booking cancelled successfully."
                        );
                    } else {
                        System.out.println(
                                "No booking found to cancel."
                        );
                    }

                    break;
                }

                case 7: {
                    System.out.println("Thank you for using the system.");
                    break;
                }

                default: {
                    System.out.println(
                            "Please select a valid option."
                    );
                    break;
                }
            }
        }
        sc.close();
    }
}