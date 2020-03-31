package ergaf.step.controllers;

import ergaf.step.entities.Passenger;
import ergaf.step.services.PassengerService;

import java.util.List;

public class PassengerController {

    private PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public Passenger addPassenger(Passenger passenger) {
        return passengerService.addPassenger(passenger);
    }

    public void saveData(List<Passenger> passengers){
        passengerService.saveData(passengers);
    }

    public void loadData(){
        passengerService.loadData(
                passengerService.prepareData()
        );
    }

    public Passenger getPassengerByFirstNameAndLastName(String firstname, String lastname) {
        return passengerService.getPassengerByFirstNameAndLastName(firstname, lastname);
    }

    public List<Passenger> getAllPassengers() {
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
