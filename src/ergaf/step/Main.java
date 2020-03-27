package ergaf.step;

import ergaf.step.booking.BookingController;
import ergaf.step.booking.BookingDao;
import ergaf.step.booking.BookingService;
import ergaf.step.console.ConsoleAuth;
import ergaf.step.input.Input;
import ergaf.step.flight.FlightDao;
import ergaf.step.flight.FlightCreator;
import ergaf.step.flight.FlightsController;
import ergaf.step.flight.FlightsService;
import ergaf.step.menu.AuthMenu;
import ergaf.step.passenger.PassengerController;
import ergaf.step.passenger.PassengerDao;
import ergaf.step.passenger.PassengerService;
import ergaf.step.user.UserController;
import ergaf.step.user.UserDao;
import ergaf.step.user.UserService;

public class Main {

    private static final int FLIGHTS_AMOUNT = 40;

    public static void main(String[] args) {

        FlightDao flightDao = new FlightDao();
        BookingDao bookingDao = new BookingDao();
        UserDao userDao = new UserDao();
        PassengerDao passengerDao = new PassengerDao();

        FlightsController flightsController = new FlightsController(
                new FlightsService(flightDao)
        );

        BookingController bookingController = new BookingController(
                new BookingService(bookingDao)
        );

        UserController userController = new UserController(
                new UserService(userDao)
        );

        PassengerController passengerController = new PassengerController(
                new PassengerService(passengerDao)
        );

        FlightCreator flightCreator = new FlightCreator(flightsController, FLIGHTS_AMOUNT);

        flightsController.loadData();
        bookingController.loadData();
        userController.loadData();
        passengerController.loadData();

        if(flightsController.getAllFlights().size() <= 0){
            flightCreator.createFlightBase();
        }

        ConsoleAuth consoleAuth = new ConsoleAuth(
                new Input(),
                userController,
                flightsController,
                bookingController,
                passengerController,
                flightCreator
        );

        System.out.print("Приложение для бронировки авиабилетов.");
        System.out.println(AuthMenu.AUTH_MENU);
        System.out.println("Please enter number from menu: ");
        String command = consoleAuth.startConsole();

        while (!command.equals("3")) { // 3 -> exit
            System.out.println("\nPlease enter number from menu: ");
            command = consoleAuth.startConsole();
        }
    }
}
