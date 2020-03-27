package ergaf.step.booking;

import ergaf.step.flight.DateGenerator;
import ergaf.step.flight.Flight;
import ergaf.step.flight.FlightDao;
import ergaf.step.flight.FlightsService;
import ergaf.step.passenger.Passenger;
import ergaf.step.passenger.PassengerDao;
import ergaf.step.passenger.PassengerService;
import ergaf.step.user.User;
import ergaf.step.user.UserDao;
import ergaf.step.user.UserService;
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
                new Booking(flight, passenger)
        );

        //then
        assertEquals(1, bookingService.count());
        assertEquals(1, bookingService.getBookingById(1).getId());
    }
}
