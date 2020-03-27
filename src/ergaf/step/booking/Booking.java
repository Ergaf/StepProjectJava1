package ergaf.step.booking;

import ergaf.step.flight.Flight;
import ergaf.step.passenger.Passenger;

import java.io.Serializable;
import java.util.Objects;

public class Booking implements Serializable {

    private int id;
    private Flight flight;
    private Passenger passenger;

    public Booking(Flight flight, Passenger passenger) {
        this.flight = flight;
        this.flight.setBookedPlaces(this.flight.getBookedPlaces()+1);
        this.passenger = passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return getId() == booking.getId() &&
                Objects.equals(getFlight(), booking.getFlight()) &&
                Objects.equals(getPassenger(), booking.getPassenger());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFlight(), getPassenger());
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", flight=" + flight +
                ", passenger=" + passenger +
                '}';
    }

    public int getId() {
        return id;
    }

    public Booking setId(int id) {
        this.id = id;
        return this;
    }
}
