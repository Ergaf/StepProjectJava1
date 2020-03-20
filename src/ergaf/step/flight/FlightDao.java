package ergaf.step.flight;

import ergaf.step.io.Logger;

import java.util.ArrayList;

public class FlightDao implements FlightBaseInterface {

    private ArrayList<Flight> flights = new ArrayList<>();

    @Override
    public ArrayList<Flight> getAllFlights() {
        Logger.info("был получен список полетов");
        return flights;
    }

    @Override
    public void addFlight(Flight flight) {
        Logger.info("addFlightToCollection");

        int index = flights.indexOf(flight);
        if (index == -1) {
            flights.add(flight);
        } else {
            flights.set(index, flight);
        }
    }

    @Override
    public void clearFlights() {
        Logger.info("clearFlightList");
        flights.clear();
    }

    @Override
    public void loadData(ArrayList<Flight> flights) {
        Logger.info("loadData");
        this.flights = flights;
    }
}
