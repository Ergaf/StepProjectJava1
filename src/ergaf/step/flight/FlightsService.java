package ergaf.step.flight;

import java.util.List;
import java.util.stream.Collectors;

public class FlightsService {

    private CollectionFlightDao flightDao;

    {
        System.out.println("создался екземпляр "+this.getClass().getSimpleName());
    }

    public FlightsService(CollectionFlightDao flightDao) {
        this.flightDao = flightDao;
    }

    public List<Flight> giveAllFlights() {
        return flightDao.giveAllFlights();
    }

    public void displayAllFlights() {
        flightDao.giveAllFlights().forEach(Flight::prettyFormat);
    }

    public Flight giveFlightForId(String id) {
        List<Flight> flights = flightDao.giveAllFlights().stream()
                .filter(e -> e.id.equals(id))
                .collect(Collectors.toList());
        return flights.get(0);
    }

    public void saveFlightToCollection(Flight flight) {
        flightDao.saveFlightToCollection(flight);
    }

    public void saveDataToFile(){
        flightDao.saveDataToFile();
    }

    public void loadDataInFile(){
        flightDao.loadDataInFile();
    }
}
