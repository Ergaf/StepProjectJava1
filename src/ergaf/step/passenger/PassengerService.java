package ergaf.step.passenger;

import ergaf.step.io.FileWorker;
import ergaf.step.user.User;

import java.util.ArrayList;

public class PassengerService {

    private PassengerDao passengerDao;

    private String filename = "passengers.data";

    public PassengerService(PassengerDao passengerDao, String filename) {
        this.passengerDao = passengerDao;
        this.filename = filename;
    }

    public PassengerService(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    public int count() {
        return passengerDao.getAllPassengers().size();
    }

    public Passenger addPassenger(Passenger passenger) {
        Passenger foundPassenger = getPassengerByFirstNameAndLastName(
                passenger.getFirstName(),
                passenger.getLastName()
        );
        if (foundPassenger != null) {
            if (foundPassenger.hasUser() && !passenger.hasUser()) {
                passenger.setUser(foundPassenger.getUser());
            }
            if (!foundPassenger.hasUser() && passenger.hasUser()) {
                foundPassenger.setUser(passenger.getUser());
            }
            passenger.setId(foundPassenger.getId());

            return passenger;
        }
        return passengerDao.addPassenger(passenger.setId(getNextId()));
    }

    public Passenger getPassengerByFirstNameAndLastName(String firstname, String lastname) {
        return passengerDao.
                getAllPassengers().
                stream().
                filter(passenger -> passenger.getFirstName().equals(firstname) && passenger.getLastName().equals(lastname)).
                findFirst().
                orElse(null);
    }

    public int getNextId() {
        int id = passengerDao.getAllPassengers().
                stream().
                mapToInt(Passenger::getId).
                reduce((first,second) -> second).orElse(0);

        return id + 1;
    }

    public void saveData(ArrayList<Passenger> passengers){
        FileWorker.serialize(filename, passengers);
    }

    public ArrayList<Passenger> prepareData() {
        return FileWorker.deserialize(filename);
    }

    public void loadData(ArrayList<Passenger> passengers){
        passengerDao.loadData(passengers);
    }

    public ArrayList<Passenger> getAllPassengers() {
        return passengerDao.getAllPassengers();
    }

    public boolean unlinkData() {
        return FileWorker.unlinkData(filename);
    }

    public void clearPassengers() {
        passengerDao.clearPassengers();
    }
}
