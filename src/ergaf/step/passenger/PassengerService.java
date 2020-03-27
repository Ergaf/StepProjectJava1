package ergaf.step.passenger;

public class PassengerService {

    private PassengerDao passengerDao;

    private String filename = "passengers.data";

    public PassengerService(PassengerDao passengerDao, String filename) {
        this.passengerDao = passengerDao;
        this.filename = filename;
    }


    public Passenger addPassenger(Passenger passenger) {
        return passengerDao.addPassenger(passenger.setId(getNextId()));
    }

    public int getNextId() {
        int id = passengerDao.getAllPassengers().
                stream().
                mapToInt(Passenger::getId).
                reduce((first,second) -> second).orElse(0);

        return id + 1;
    }
}
