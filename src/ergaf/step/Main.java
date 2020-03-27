package ergaf.step;

import ergaf.step.controllers.BookingController;
import ergaf.step.dao.BookingDao;
import ergaf.step.services.BookingService;
import ergaf.step.console.ConsoleAuth;
import ergaf.step.utils.input.Input;
import ergaf.step.dao.FlightDao;
import ergaf.step.utils.FlightCreator;
import ergaf.step.controllers.FlightsController;
import ergaf.step.services.FlightsService;
import ergaf.step.menu.AuthMenu;
import ergaf.step.controllers.PassengerController;
import ergaf.step.dao.PassengerDao;
import ergaf.step.services.PassengerService;
import ergaf.step.controllers.UserController;
import ergaf.step.dao.UserDao;
import ergaf.step.services.UserService;

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
