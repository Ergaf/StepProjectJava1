package ergaf.step.entities;

import java.io.Serializable;
import java.util.Objects;

public class Booking implements Serializable {

    private int id;
    private Flight flight;
    private Passenger passenger;

    private User user;

    public Booking(User user, Flight flight, Passenger passenger) {
        this.user = user;
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

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return getId() == booking.getId() &&
                getFlight().equals(booking.getFlight()) &&
                getPassenger().equals(booking.getPassenger()) &&
                getUser().equals(booking.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFlight(), getPassenger(), getUser());
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
