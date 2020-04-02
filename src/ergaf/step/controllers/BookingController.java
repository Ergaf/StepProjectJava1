package ergaf.step.controllers;


import ergaf.step.entities.Booking;
import ergaf.step.entities.Passenger;
import ergaf.step.services.BookingService;
import ergaf.step.entities.User;

import java.util.List;

public class BookingController {
    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public Booking addBooking(Booking booking) {
        return bookingService.addBooking(booking);
    }

    public int count() {
        return bookingService.count();
    }

    public List<Booking> getAllBookings()
    {
        return bookingService.getAllBookings();
    }

    public void saveData(List<Booking> bookings){
        bookingService.saveData(bookings);
    }

    public void loadData(){
        bookingService.loadData(
                bookingService.prepareData()
        );
    }

    public Booking getBookingById(int id) {
        return bookingService.getBookingById(id);
    }

    public List<Booking> getBookingsByOwner(User user) {
        return bookingService.getBookingsByUser(user);
    }

    public List<Booking> getBookingsByUser(User user) {
        return bookingService.getBookingsByFirstAndLastNames(user.getFirstName(), user.getLastName());
    }

    public List<Booking> getBookingsByPassenger(Passenger passenger) {
        return bookingService.getBookingsByFirstAndLastNames(passenger.getFirstName(), passenger.getLastName());
    }

    public void displayFlights(List<Booking> bookings) {
        bookingService.displayFlights(bookings);
    }

    public boolean cancelBookingById(int id) {
        return bookingService.cancelBookingById(id);
    }

    public boolean unlinkData() {
        return bookingService.unlinkData();
    }

    public void clearBookings() {
        bookingService.clearBookings();
    }
}
