package ergaf.step.dao;

import ergaf.step.utils.io.Logger;
import ergaf.step.entities.Passenger;

import java.util.ArrayList;
import java.util.List;

public class PassengerDao implements Dao<Passenger> {

    private List<Passenger> passengers = new ArrayList<>();

    @Override
    public List<Passenger> getAll() {
        Logger.info("был получен список passengers");
        return passengers;
    }

    @Override
    public Passenger add(Passenger passenger) {
        Logger.info("addPassengerToCollection");

        int index = passengers.indexOf(passenger);
        if (index == -1) {
            passengers.add(passenger);
        } else {
            passengers.set(index, passenger);
        }
        return passenger;
    }

    @Override
    public void clear() {
        Logger.info("clearPassengerList");
        passengers.clear();
    }

    @Override
    public void loadData(List<Passenger> passengers) {
        Logger.info("loadData");
        this.passengers = passengers;
    }

    @Override
    public boolean delete(Passenger passenger) {
        return passengers.remove(passenger);
    }

}
