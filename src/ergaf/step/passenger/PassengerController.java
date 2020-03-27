package ergaf.step.passenger;

import java.util.ArrayList;

public class PassengerController {

    private PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public Passenger addPassenger(Passenger passenger) {
        return passengerService.addPassenger(passenger);
    }

    public void saveData(ArrayList<Passenger> passengers){
        passengerService.saveData(passengers);
    }

    public void loadData(){
        passengerService.loadData(
                passengerService.prepareData()
        );
    }

    public ArrayList<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    public boolean unlinkData() {
        return passengerService.unlinkData();
    }

    public void clearPassengers() {
        passengerService.clearPassengers();
    }

    public int count() {
        return passengerService.count();
    }
}
