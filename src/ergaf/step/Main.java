package ergaf.step;

import ergaf.step.booking.BookingController;
import ergaf.step.booking.BookingService;
import ergaf.step.console.ConsoleMain;
import ergaf.step.flight.CollectionFlightDao;
import ergaf.step.flight.FlightsController;
import ergaf.step.flight.FlightsService;

public class Main {

    public static void main(String[] args) {

        CollectionFlightDao collectionFlightDao = new CollectionFlightDao();

        FlightsController flightsController = new FlightsController(
                new FlightsService(collectionFlightDao)
        );

        BookingController bookingController = new BookingController(
                new BookingService(collectionFlightDao)
        );

        ConsoleMain console = new ConsoleMain(
                flightsController,
                bookingController
        );
        console.startConsole();
    }
}
