package ergaf.step.flight;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FlightsController {
    private FlightsService service;

    public FlightsController(FlightsService service) {
        this.service = service;
    }

    public ArrayList<Flight> getAllFlights()
    {
        return service.getAllFlights();
    }

    public List<Flight> getFlightsByRange(LocalDateTime start, LocalDateTime end)
    {
        return service.getFlightsByRange(start, end);
    }

    public void displayAllFlights() {
        service.displayAllFlights();
    }

    public void displayFlights(List<Flight> flights) {
        service.displayFlights(flights);
    }

    public List<Flight> searchFlights(String from, String to, LocalDate at, int ticketsAmount) {
        return service.searchFlights(from, to, at, ticketsAmount);
    }

    public Flight getFlightById(int id) {
        return service.getFlightById(id);
    }

    public int count() {
        return service.count();
    }

    public void clearFlights() {
        service.clearFlights();
    }

    public boolean unlinkData() {
        return service.unlinkData();
    }

    public void addFlight(Flight flight) {
        service.addFlight(flight);
    }

    public void saveData(ArrayList<Flight> flights){
        service.saveData(flights);
    }

    public void loadData(){
        service.loadData(
                service.prepareData()
        );
    }
}
