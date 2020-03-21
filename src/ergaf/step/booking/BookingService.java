package ergaf.step.booking;

import ergaf.step.io.FileWorker;
import ergaf.step.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookingService {

    private BookingDao bookingDao;

    private String filename = "bookings.data";

    public BookingService(BookingDao bookingDao) {
        this.bookingDao = bookingDao;
    }

    public BookingService(BookingDao bookingDao, String filename) {

        this.bookingDao = bookingDao;
        this.filename = filename;
    }

    public Booking getBookingById(int id) {
        return bookingDao.
                getAllBookings().
                stream().
                filter(booking -> booking.getId() == id).
                findFirst().
                orElse(null);
    }

    public int count() {
        return getAllBookings().size();
    }

    public int getNextId() {
        int id = bookingDao.getAllBookings().
                stream().
                mapToInt(Booking::getId).
                reduce((first,second) -> second).orElse(0);

        return id + 1;
    }

    public Booking addBooking(Booking booking) {
        return bookingDao.addBooking(booking.setId(getNextId()));
    }

    public ArrayList<Booking> getAllBookings() {
        return bookingDao.getAllBookings();
    }

    public void saveData(ArrayList<Booking> bookings){
        FileWorker.serialize(filename, bookings);
    }

    public ArrayList<Booking> prepareData() {
        return FileWorker.deserialize(filename);
    }

    public void loadData(ArrayList<Booking> bookings){
        bookingDao.loadData(bookings);
    }

    public List<Booking> getBookingsByUser(User user) {
        return bookingDao.
                getAllBookings().
                stream().
                filter(booking -> booking.getUser().equals(user)).
                collect(Collectors.toList());
    }

}
