package ergaf.step.passenger;

import ergaf.step.io.Logger;

import java.util.ArrayList;

public class PassengerDao implements PassengerBaseInterface {

    private ArrayList<Passenger> passengers = new ArrayList<>();

    @Override
    public ArrayList<Passenger> getAllPassengers() {
        Logger.info("был получен список passengers");
        return passengers;
    }

    @Override
    public Passenger addPassenger(Passenger passenger) {
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
    public void clearPassengers() {
        Logger.info("clearPassengerList");
        passengers.clear();
    }

    @Override
    public void loadData(ArrayList<Passenger> passengers) {
        Logger.info("loadData");
        this.passengers = passengers;
    }
}
