package ergaf.step.flight;

import ergaf.step.LogOrError;
import ergaf.step.Logger;

import java.util.ArrayList;

public class CollectionFlightDao implements FlightBaseInterface {

    private ArrayList<Flight> flights = new ArrayList<>();
    Logger logger = new Logger();

    @Override
    public ArrayList<Flight> getAllFlights() {
        logger.log(LogOrError.LOG, "был получен список полетов");
        return flights;
    }

    @Override
    public void saveFlightToCollection(Flight flight) {
        logger.log(LogOrError.LOG, "saveFlightToCollection");
        flights.add(flight);
    }

    @Override
    public void clearFlightList() {
        logger.log(LogOrError.LOG, "clearFlightList");
        flights.clear();
    }

    @Override
    public void loadData(ArrayList<Flight> flights) {
        logger.log(LogOrError.LOG, "loadData");
        this.flights = flights;
    }
}
