package ergaf.step.dao;

import ergaf.step.entities.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingDao implements Dao<Booking> {

    private List<Booking> bookings = new ArrayList<>();

    @Override
    public List<Booking> getAll() {
        return bookings;
    }

    @Override
    public Booking add(Booking booking) {
        int index = bookings.indexOf(booking);
        if (index == -1) {
            bookings.add(booking);
        } else {
            bookings.set(index, booking);
        }
        return booking;
    }

    @Override
    public void clear() {
        bookings.clear();
    }

    @Override
    public void loadData (List<Booking> bookings) {
        this.bookings = bookings;
    }

    @Override
    public boolean delete(Booking booking) {
        return bookings.remove(booking);
    }

}
