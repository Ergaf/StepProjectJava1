package ergaf.step;

import ergaf.step.booking.BookingController;
import ergaf.step.booking.BookingDao;
import ergaf.step.booking.BookingService;
import ergaf.step.console.ConsoleMain;
import ergaf.step.input.Input;
import ergaf.step.flight.FlightDao;
import ergaf.step.flight.FlightCreator;
import ergaf.step.flight.FlightsController;
import ergaf.step.flight.FlightsService;
import ergaf.step.menu.Menu;
import ergaf.step.user.UserController;
import ergaf.step.user.UserDao;
import ergaf.step.user.UserService;

public class Main {

    private static final int FLIGHTS_AMOUNT = 20;

    public static void main(String[] args) {

        FlightDao flightDao = new FlightDao();
        BookingDao bookingDao = new BookingDao();
        UserDao userDao = new UserDao();

        FlightsController flightsController = new FlightsController(
                new FlightsService(flightDao)
        );

        BookingController bookingController = new BookingController(
                new BookingService(bookingDao)
        );

        UserController userController = new UserController(
                new UserService(userDao)
        );
        FlightCreator flightCreator = new FlightCreator(flightsController, FLIGHTS_AMOUNT);

        flightsController.loadData();
        bookingController.loadData();
        userController.loadData();

        if(flightsController.getAllFlights().size() <= 0){
            flightCreator.createFlightBase();
        }

        ConsoleMain console = new ConsoleMain(
                flightsController,
                bookingController,
                userController,
                new Input(),
                flightCreator
        );

        System.out.println(Menu.MENU);
        System.out.println("Please enter number from menu: ");
        String command = console.startConsole();

        while (!command.equals("7")) { // 7 -> exit
            System.out.println("\nPlease enter number from menu: ");
            command = console.startConsole();
        }
    }
}
