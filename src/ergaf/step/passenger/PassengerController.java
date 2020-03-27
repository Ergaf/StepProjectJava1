package ergaf.step.passenger;

public class PassengerController {

    private PassengerService passengerService;

    public PassengerController(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    public Passenger addPassenger(Passenger passenger) {
        return passengerService.addPassenger(passenger);
    }
}
