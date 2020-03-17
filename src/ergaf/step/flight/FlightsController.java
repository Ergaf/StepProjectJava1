package ergaf.step.flight;

import java.util.ArrayList;
import java.util.List;

public class FlightsController {
    FlightsService service;

    public FlightsController(FlightsService service) {
        this.service = service;
    }

    public ArrayList<Flight> getAllFlights()
    {
        return service.getAllFlights();
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

    public void saveData(ArrayList<Flight> flights){
        service.saveData(flights);
    }

    public void loadData(){
        service.loadData(
                service.prepareData()
        );
    }
}
