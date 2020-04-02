package ergaf.step.services;

import ergaf.step.dao.Dao;
import ergaf.step.entities.Booking;
import ergaf.step.dao.BookingDao;
import ergaf.step.entities.Flight;
import ergaf.step.utils.io.FileWorker;
import ergaf.step.entities.Passenger;
import ergaf.step.entities.User;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BookingService {

    private Dao<Booking> bookingDao;

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
                getAll().
                stream().
                filter(booking -> booking.getId() == id).
                findFirst().
                orElse(null);
    }

    public int count() {
        return getAllBookings().size();
    }

    public int getNextId() {
        int id = bookingDao.getAll().
                stream().
                mapToInt(Booking::getId).
                reduce((first,second) -> second).orElse(0);

        return id + 1;
    }

    public Booking addBooking(Booking booking) {
        if (getBookingByPassengerAndFlight(booking.getPassenger(), booking.getFlight()) != null) {
            return booking;
        }
        return bookingDao.add(booking.setId(getNextId()));
    }

    public Booking getBookingByPassengerAndFlight(Passenger passenger, Flight flight) {
        return bookingDao.
                getAll().
                stream().
                filter(booking -> booking.getPassenger().equals(passenger) &&
                        booking.getFlight().equals(flight)).
                findFirst().
                orElse(null);
    }

    public List<Booking> getAllBookings() {
        return bookingDao.getAll();
    }

    public void saveData(List<Booking> bookings){
        FileWorker.serialize(filename, bookings);
    }

    public List<Booking> prepareData() {
        return FileWorker.deserialize(filename);
    }

    public void loadData(List<Booking> bookings){
        bookingDao.loadData(bookings);
    }

    public List<Booking> getBookingsByUser(User user) {
        List<Booking> listOne = bookingDao.
                getAll().
                stream().
                filter(
                        booking -> booking.getUser().equals(user)
                ).
                collect(Collectors.toList());

        return Stream.concat(
                listOne.stream(),
                getBookingsByFirstAndLastNames(user.getFirstName(), user.getLastName()).stream())
                .collect(Collectors.toList());
    }

    public List<Booking> getBookingsByFirstAndLastNames(String firstname, String lastname) {
        return bookingDao.
                getAll().
                stream().
                filter(booking ->
                        booking.
                                getPassenger().
                                getFirstName().
                                equals(firstname) &&
                                booking.
                                        getPassenger().
                                        getLastName().
                                        equals(lastname)
                ).
                collect(Collectors.toList());
    }

    public List<Booking> getBookingsByFlight(Flight flight) {
        return bookingDao.
                getAll().
                stream().
                filter(booking ->booking.getFlight().equals(flight)).
                collect(Collectors.toList());
    }

    public boolean cancelBookingById(int id) {
        Booking booking = getBookingById(id);
        if (booking != null) {
            booking.getFlight().setBookedPlaces(
                    booking.getFlight().getBookedPlaces()-1
            );
        }
        return bookingDao.delete(booking);
    }

    public void displayFlights(List<Booking> bookings) {
        bookings.forEach(booking -> System.out.println(
                "\t" +
                        booking.getId() +
                        ") " +
                        booking.getFlight().prettyFormat()
                )
        );
    }

    public boolean unlinkData() {
        return FileWorker.unlinkData(filename);
    }

    public void clearBookings() {
        bookingDao.clear();
    }
}
