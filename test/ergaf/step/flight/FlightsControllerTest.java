package ergaf.step.flight;

import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.*;

public class FlightsControllerTest {

    FlightsController flightsController;
    DateGenerator dateGenerator;

    private final static int FLIGHTS_AMOUNT = 30;
    private final static String TEST_DB = "flights.test.data";

    @Before
    public void executedBeforeEach() {
        flightsController = new FlightsController(
                new FlightsService(
                        new FlightDao(),
                        TEST_DB
                )
        );
        FlightCreator flightCreator = new FlightCreator(flightsController, FLIGHTS_AMOUNT);
        flightCreator.createFlightBase();

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
    public void get_all_flights_should_return_correct_amount_of_flights() {
        //given
        //when
        //then
        assertEquals(FLIGHTS_AMOUNT, flightsController.getAllFlights().size());
    }

    @Test
    public void get_flight_by_id_should_return_correct_flight_when_called() {
        //given
        Flight flight = flightsController.getFlightById(1);
        //when
        //then
        assertEquals(1, flight.getId());
    }

    @Test
    public void get_flight_by_id_should_return_null_when_flight_does_not_exists() {
        //given
        Flight flight = flightsController.getFlightById(0);
        //when
        //then
        assertNull(flight);
    }

    @Test
    public void save_flight_should_append_flight_to_the_dao() {
        //given
        Flight flight = new Flight(
                FlightCreator.DEPARTURE,
                "Boyarka",
                dateGenerator.getRandomFlightLocalDateTime(),
                55
        );
        //when
        flightsController.addFlight(flight);
        //then
        assertEquals(FLIGHTS_AMOUNT+1, flightsController.count());
    }


    @Test
    public void save_flight_should_replace_flight_in_the_dao_when_equal_flight_added() {
        //given
        Flight flight = flightsController.getFlightById(1);
        //when
        flightsController.addFlight(flight);
        //then
        assertEquals(FLIGHTS_AMOUNT, flightsController.count());
    }

    @Test
    public void save_data_generates_db_when_called() {
        //given
        ArrayList<Flight> flights = flightsController.getAllFlights();
        //when
        flightsController.saveData(flights);
        //then
        flightsController.loadData();
        assertEquals(flights, flightsController.getAllFlights());
    }

    @Test
    public void search_flights_return_flight_when_called_with_correct_parameters() {
        //given
        Flight flight = flightsController.getFlightById(1);
        //when
        List<Flight> flights = flightsController.searchFlights(
                FlightCreator.DEPARTURE,
                flight.getTo(),
                flight.getAt().toLocalDate(),
                flight.getFreePlaces() - 1
        );
        //then
        assertTrue(flights.size() > 0);
    }

    @Test
    public void search_flights_return_no_flights_when_called_with_more_free_seats() {
        //given
        Flight flight = flightsController.getFlightById(1);
        //when
        List<Flight> flights = flightsController.searchFlights(
                FlightCreator.DEPARTURE,
                flight.getTo(),
                flight.getAt().toLocalDate(),
                999
        );
        //then
        assertEquals(0, flights.size());
    }

    @Test
    public void search_flights_return_flights_when_called_with_equal_free_seats() {
        //given
        Flight flight = flightsController.getFlightById(1);
        //when
        List<Flight> flights = flightsController.searchFlights(
                FlightCreator.DEPARTURE,
                flight.getTo(),
                flight.getAt().toLocalDate(),
                flight.getFreePlaces()
        );
        //then
        assertTrue(flights.size() > 0);
    }

    @Test
    public void search_cross_flights_return_them() {
        //given
        Flight flight = flightsController.getFlightById(1);
        System.out.println(flight);
        flightsController.displayAllFlights();
        //when
        List<Flight> flights = flightsController.searchFlights(
                FlightCreator.DEPARTURE,
                flight.getTo(),
                flight.getAt().toLocalDate(),
                1
        );
        //then
        assertTrue(flights.size() > 0);
    }

    @Test
    public void get_flights_by_range_return_flights() {
        //given
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusDays(1);
        List<Flight> flights = flightsController.getFlightsByRange(start, end);
        //when
        //then
        flights.forEach(
                flight -> assertTrue(
                        flight.getAt().isAfter(start) && flight.getAt().isBefore(end)
                ));
    }

    @Test
    public void search_flight_affected_by_booked_places() {
        //given
        flightsController.clearFlights();
        flightsController.addFlight(new Flight(
                "LA",
                "SF",
                LocalDateTime.now(),
                2
        ));
        Flight flight = flightsController.getFlightById(1);
        //when
        flight.setBookedPlaces(2);
        List<Flight> flights = flightsController.searchFlights(
                "LA",
                "SF",
                flight.getAt().toLocalDate(),
                1
        );
        //then
        assertEquals(0, flights.size());
    }

}
