package ergaf.step.services;

import ergaf.step.dao.Dao;
import ergaf.step.utils.io.FileWorker;
import ergaf.step.entities.Passenger;
import ergaf.step.dao.PassengerDao;

import java.util.List;

public class PassengerService {

    private Dao<Passenger> passengerDao;

    private String filename = "passengers.data";

    public PassengerService(PassengerDao passengerDao, String filename) {
        this.passengerDao = passengerDao;
        this.filename = filename;
    }

    public PassengerService(PassengerDao passengerDao) {
        this.passengerDao = passengerDao;
    }

    public int count() {
        return passengerDao.getAll().size();
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
        return passengerDao.add(passenger.setId(getNextId()));
    }

    public Passenger getPassengerByFirstNameAndLastName(String firstname, String lastname) {
        return passengerDao.
                getAll().
                stream().
                filter(passenger -> passenger.getFirstName().equals(firstname) && passenger.getLastName().equals(lastname)).
                findFirst().
                orElse(null);
    }

    public int getNextId() {
        int id = passengerDao.getAll().
                stream().
                mapToInt(Passenger::getId).
                reduce((first,second) -> second).orElse(0);

        return id + 1;
    }

    public void saveData(List<Passenger> passengers){
        FileWorker.serialize(filename, passengers);
    }

    public List<Passenger> prepareData() {
        return FileWorker.deserialize(filename);
    }

    public void loadData(List<Passenger> passengers){
        passengerDao.loadData(passengers);
    }

    public List<Passenger> getAllPassengers() {
        return passengerDao.getAll();
    }

    public boolean unlinkData() {
        return FileWorker.unlinkData(filename);
    }

    public void clearPassengers() {
        passengerDao.clear();
    }
}
