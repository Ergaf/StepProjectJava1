package ergaf.step.passenger;

import java.util.ArrayList;

public interface PassengerBaseInterface {
    public ArrayList<Passenger> getAllPassengers();
    public Passenger addPassenger(Passenger passenger);
    public void clearPassengers();
    public void loadData(ArrayList<Passenger> passengers);
}
