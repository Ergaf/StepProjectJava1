package ergaf.step;

import ergaf.step.booking.BookingController;
import ergaf.step.booking.BookingDao;
import ergaf.step.booking.BookingService;
import ergaf.step.input.Input;
import ergaf.step.flight.FlightDao;
import ergaf.step.flight.FlightCreator;
import ergaf.step.flight.FlightsController;
import ergaf.step.flight.FlightsService;

public class Main {

    private static final int FLIGHTS_AMOUNT = 20;

    public static void main(String[] args) {

        FlightDao flightDao = new FlightDao();
        BookingDao bookingDao = new BookingDao();

        FlightsController flightsController = new FlightsController(
                new FlightsService(flightDao)
        );

        BookingController bookingController = new BookingController(
                new BookingService(bookingDao)
        );

        FlightCreator flightCreator = new FlightCreator(flightsController, FLIGHTS_AMOUNT);

        flightsController.loadData();
        if(flightsController.getAllFlights().size() <= 0){
            flightCreator.createFlightBase();
        }

        ConsoleMain console = new ConsoleMain(
                flightsController,
                bookingController,
                new Input()
        );

        System.out.println(Menu.MENU);
        System.out.println("Please enter number from menu: ");
        String command = console.startConsole();

        while (!command.equals("6")) { // 6 -> exit
            System.out.println("\nPlease enter number from menu: ");
            command = console.startConsole();
        }
    }
}
