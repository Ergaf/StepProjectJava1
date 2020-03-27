package ergaf.step.flight;

import ergaf.step.io.FileWorker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightsService {

    private FlightDao flightDao;

    private String filename = "flights.data";

    public FlightsService(FlightDao flightDao) {

        this.flightDao = flightDao;
    }

    public FlightsService(FlightDao flightDao, String filename) {

        this.flightDao = flightDao;
        this.filename = filename;
    }

    public ArrayList<Flight> getAllFlights() {
        return flightDao.getAllFlights();
    }

    public List<Flight> searchFlights(String from, String to, LocalDate at, int ticketsAmount) {
        // First search direct connections from Kyiv to Germany
        List<Flight> directFlights = searchDirectFlights(from, to, at, ticketsAmount);

        // Result list
        List<Flight> result = new ArrayList<>(directFlights);

        //Then search for flights to Germany not from Kyiv
        List<Flight> excludeFlights = searchFlightsByDestinationExcludeFrom(from, to, at, ticketsAmount);

        //Then loop over found flights and check do they have connections with Kyiv
        List<Flight> crossFlights = excludeFlights.stream().filter(flight1 -> {
            Flight flight = getAllFlights().stream().filter(flight2 -> {
                return flight2.getFrom().equals(from) &&
                        flight2.getTo().equals(flight1.getFrom()) &&
                        flight2.getAt().toLocalDate().equals(at) &&
                        flight2.getFreePlaces() >= ticketsAmount;
            }).findFirst().orElse(null);
            if (flight != null) {
                result.add(flight);
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        result.addAll(crossFlights);
        return result;
    }

    public List<Flight> getFlightsByRange(LocalDateTime start, LocalDateTime end)
    {
        return getAllFlights().stream().filter(flight -> flight.getAt().isAfter(start) &&
                        flight.getAt().isBefore(end)
                ).collect(Collectors.toList());
    }

    public List<Flight> searchDirectFlights(String from, String to, LocalDate at, int ticketsAmount) {
        return getAllFlights().stream().filter(flight -> flight.getTo().equals(to) &&
                flight.getFrom().equals(from) &&
                flight.getAt().toLocalDate().equals(at) &&
                flight.getFreePlaces() >= ticketsAmount).collect(Collectors.toList());
    }

    public List<Flight> searchFlightsByDestinationExcludeFrom(String from, String to, LocalDate at, int ticketsAmount) {
        return getAllFlights().stream().filter(flight -> flight.getTo().equals(to) &&
                !flight.getFrom().equals(from) &&
                flight.getAt().toLocalDate().equals(at) &&
                flight.getFreePlaces() >= ticketsAmount).collect(Collectors.toList());
    }

    public void displayAllFlights() {
        displayFlights(flightDao.getAllFlights());
    }

    public void displayFlights(List<Flight> flights) {
        List<String> collect = flights.stream().map(flight -> flight.getId() + ")\t" + flight.prettyFormat())
                .collect(Collectors.toList());

        collect.forEach(System.out::println);
    }

    public Flight getFlightById(int id) {
        return flightDao.
                getAllFlights().
                stream().
                filter(flight -> flight.getId() == id).
                findFirst().
                orElse(null);
    }

    public Flight addFlight(Flight flight) {
        return flightDao.addFlight(flight.setId(getNextId()));
    }

    public int getNextId() {
        int id = flightDao.getAllFlights().
                stream().
                mapToInt(Flight::getId).
                reduce((first,second) -> second).orElse(0);

        return id + 1;
    }

    public int count() {
        return flightDao.getAllFlights().size();
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

    public void clearFlights() {
        flightDao.clearFlights();
    }

    public boolean unlinkData() {
        return FileWorker.unlinkData(filename);
    }

}
