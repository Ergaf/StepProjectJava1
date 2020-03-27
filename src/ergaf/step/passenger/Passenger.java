package ergaf.step.passenger;

import ergaf.step.user.User;

import java.io.Serializable;
import java.util.Objects;

public class Passenger implements Serializable  {

    private int id;
    private String firstName;
    private String lastName;
    private User user;

    public Passenger(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Passenger(User user) {
        this.user = user;
        this.firstName = this.user.getFirstName();
        this.lastName = this.user.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String prettyFormat() {
        return firstName + " " + lastName;
    }

    public int getId() {
        return id;
    }

    public Passenger setId(int id) {
        this.id = id;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public boolean hasUser() {
        return user != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return getId() == passenger.getId() &&
                Objects.equals(getFirstName(), passenger.getFirstName()) &&
                Objects.equals(getLastName(), passenger.getLastName()) &&
                Objects.equals(getUser(), passenger.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getUser());
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", user=" + user +
                '}';
    }
}
