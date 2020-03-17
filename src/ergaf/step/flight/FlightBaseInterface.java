package ergaf.step.flight;

import java.util.ArrayList;

public interface FlightBaseInterface {
    ArrayList<Flight> getAllFlights();
    void saveFlightToCollection(Flight flight);
    void clearFlightList();
    void loadData(ArrayList<Flight> flights);
}
