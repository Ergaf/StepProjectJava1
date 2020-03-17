package ergaf.step.flight;

import ergaf.step.io.FileWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightsService {

    private CollectionFlightDao flightDao;

    private String filename = "step.data";

    {
        System.out.println("создался екземпляр "+this.getClass().getSimpleName());
    }

    public FlightsService(CollectionFlightDao flightDao) {

        this.flightDao = flightDao;
    }

    public FlightsService(CollectionFlightDao flightDao, String filename) {

        this.flightDao = flightDao;
        if (filename != null) {
            this.filename = filename;
        }
    }

    public ArrayList<Flight> getAllFlights() {
        return flightDao.getAllFlights();
    }

    public void displayAllFlights() {
        flightDao.getAllFlights().forEach(Flight::prettyFormat);
    }

    public Flight giveFlightForId(String id) {
        List<Flight> flights = flightDao.getAllFlights().stream()
                .filter(e -> e.id.equals(id))
                .collect(Collectors.toList());
        return flights.get(0);
    }

    public void saveFlightToCollection(Flight flight) {
        flightDao.saveFlightToCollection(flight);
    }

    public void saveData(ArrayList<Flight> flights){
        FileWorker.serialize(filename, flights);
    }

    public ArrayList<Flight> prepareData() {
        return FileWorker.deserialize(filename);
    }

    public void loadData(ArrayList<Flight> flights){
        flightDao.loadData(flights);
    }
}
