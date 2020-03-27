package ergaf.step.flight;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Flight implements Serializable {

    private int id;
    private String from;
    private String to;
    private LocalDateTime at;
    private int freePlaces;
    private int bookedPlaces;

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public LocalDateTime getAt() {
        return at;
    }

    public int getId() {
        return id;
    }

    public int getFreePlaces() {
        return freePlaces - bookedPlaces;
    }

    public Flight(String from, String to, LocalDateTime at, int freePlaces) {
        this.from = from;
        this.to = to;
        this.at = at;
        this.freePlaces = freePlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flight flight = (Flight) o;
        return getId() == flight.getId() &&
                getFrom().equals(flight.getFrom()) &&
                getTo().equals(flight.getTo()) &&
                getAt().equals(flight.getAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFrom(), getTo(), getAt(), getFreePlaces());
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", at=" + at +
                ", freePlaces=" + freePlaces +
                '}';
    }

    public String prettyFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateGenerator.DATE_PATTERN);

        return "\t" + String.format("%1$-" + 10 + "s", getFrom()) +
                "\t -> \t" +
                String.format("%1$-" + 10 + "s", getTo()) +
                "\t" + formatter.format(getAt()) + " " +
                "\t Free seats: " + getFreePlaces();
    }

    public Flight setId(int id) {
        this.id = id;
        return this;
    }

    public int getBookedPlaces() {
        return bookedPlaces;
    }

    public Flight setBookedPlaces(int bookedPlaces) {
        this.bookedPlaces = bookedPlaces;
        return this;
    }
}
