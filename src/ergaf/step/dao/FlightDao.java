package ergaf.step.dao;

import ergaf.step.entities.Flight;
import ergaf.step.utils.io.Logger;

import java.util.ArrayList;
import java.util.List;

public class FlightDao implements Dao<Flight> {

    private List<Flight> flights = new ArrayList<>();

    @Override
    public List<Flight> getAll() {
        Logger.info("был получен список полетов");
        return flights;
    }

    @Override
    public Flight add(Flight flight) {
        Logger.info("addFlightToCollection");

        int index = flights.indexOf(flight);
        if (index == -1) {
            flights.add(flight);
        } else {
            flights.set(index, flight);
        }
        return flight;
    }

    @Override
    public void clear() {
        Logger.info("clearFlightList");
        flights.clear();
    }

    @Override
    public void loadData(List<Flight> flights) {
        Logger.info("loadData");
        this.flights = flights;
    }

    @Override
    public boolean delete(Flight flight) {
        return flights.remove(flight);
    }

}
