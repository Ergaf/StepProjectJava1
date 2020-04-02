package ergaf.step.services;

import ergaf.step.entities.Booking;
import ergaf.step.dao.BookingDao;
import ergaf.step.utils.DateGenerator;
import ergaf.step.entities.Flight;
import ergaf.step.dao.FlightDao;
import ergaf.step.entities.Passenger;
import ergaf.step.dao.PassengerDao;
import ergaf.step.entities.User;
import ergaf.step.dao.UserDao;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.*;

public class BookingServiceTest {

    BookingService bookingService;
    UserService userService;
    FlightsService flightsService;
    DateGenerator dateGenerator;
    PassengerService passengerService;

    @Before
    public void executedBeforeEach() {
        bookingService = new BookingService(
                new BookingDao()
        );
        userService = new UserService(
                new UserDao()
        );
        flightsService = new FlightsService(
                new FlightDao()
        );
        passengerService = new PassengerService(
                new PassengerDao()
        );

        try {
            dateGenerator = new DateGenerator(
                    "31/12/1998 10:00",
                    "31/12/2020 10:00"
            );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void get_bookings_should_return_bookings() {
        //given
        User user = userService.addUser(
                new User("A", "B")
        );
        Passenger passenger = passengerService.addPassenger(new Passenger(user));
        Flight flight = flightsService.addFlight(
                new Flight("Kyiv",
                        "London",
                        dateGenerator.getRandomFlightLocalDateTime(),
                        12
                )
        );
        //when
        Booking booking = bookingService.addBooking(
                new Booking(user, flight, passenger)
        );

        //then
        assertEquals(1, bookingService.count());
        assertEquals(1, bookingService.getBookingById(1).getId());
    }

    @Test
    public void get_bookings_by_flight_works_correctly() {
        //given
        User user = userService.addUser(
                new User("A", "B")
        );
        Passenger passenger = passengerService.addPassenger(new Passenger("A", "B"));
        Passenger passenger2 = passengerService.addPassenger(new Passenger("B", "C"));
        Flight flight1 = flightsService.addFlight(
                new Flight("Kyiv",
                        "London",
                        dateGenerator.getRandomFlightLocalDateTime(),
                        2
                )
        );
        Flight flight2 = flightsService.addFlight(
                new Flight("Kyiv",
                        "LA",
                        dateGenerator.getRandomFlightLocalDateTime(),
                        2
                )
        );
        Flight flight3 = flightsService.addFlight(
                new Flight("Kyiv",
                        "SF",
                        dateGenerator.getRandomFlightLocalDateTime(),
                        2
                )
        );
        //when
        bookingService.addBooking(new Booking(user, flight1, passenger));
        bookingService.addBooking(new Booking(user, flight1, passenger2));
        bookingService.addBooking(new Booking(user, flight2, passenger));

        //then
        assertEquals(2, bookingService.getBookingsByFlight(flight1).size());
        assertEquals(1, bookingService.getBookingsByFlight(flight2).size());
        assertEquals(0, bookingService.getBookingsByFlight(flight3).size());
    }

    @Test
    public void check_free_places_after_booking() {
        //given
        User user = userService.addUser(
                new User("A", "B")
        );
        Passenger passenger = passengerService.addPassenger(new Passenger("B", "C"));
        Flight flight = flightsService.addFlight(
                new Flight("Kyiv",
                        "London",
                        dateGenerator.getRandomFlightLocalDateTime(),
                        2
                )
        );
        //when
        //then
        assertEquals(2, flightsService.getFlightById(1).getFreePlaces());
        bookingService.addBooking(new Booking(user, flight, passenger));
        assertEquals(1, flightsService.getFlightById(1).getFreePlaces());
    }

    @Test
    public void cancelling_booking_increase_amount_of_booked_places() {
        //given
        User user = userService.addUser(
                new User("A", "B")
        );
        Passenger passenger = passengerService.addPassenger(new Passenger("B", "C"));
        Flight flight = flightsService.addFlight(
                new Flight("Kyiv",
                        "London",
                        dateGenerator.getRandomFlightLocalDateTime(),
                        2
                )
        );
        //when
        bookingService.addBooking(new Booking(user, flight, passenger));
        //then
        assertEquals(1, flightsService.getFlightById(1).getFreePlaces());
        bookingService.cancelBookingById(1);
        assertEquals(2, flightsService.getFlightById(1).getFreePlaces());
    }

}
