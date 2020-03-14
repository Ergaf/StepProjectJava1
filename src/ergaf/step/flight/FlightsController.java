package ergaf.step.flight;

import java.util.List;

public class FlightsController {
    FlightsService service;

    {
        System.out.println("создался екземпляр "+this.getClass().getSimpleName());
    }

    public FlightsController(FlightsService service) {
        this.service = service;
    }

    public List<Flight> giveAllFlights() {
        return service.giveAllFlights();
    }

    public void displayAllFlights() {
        service.displayAllFlights();
    }

    public Flight giveFlightForId(String id) {
        return service.giveFlightForId(id);
    }

    public void saveFlightToCollection(Flight flight) {
        service.saveFlightToCollection(flight);
    }

    public void saveDataToFile(){
        service.saveDataToFile();
    }

    public void loadDataInFile(){
        service.loadDataInFile();
    }
}
