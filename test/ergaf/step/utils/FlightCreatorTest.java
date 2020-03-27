package ergaf.step.utils;

import ergaf.step.controllers.FlightsController;
import ergaf.step.dao.FlightDao;
import ergaf.step.services.FlightsService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FlightCreatorTest {

    private FlightsController flightsController;
    private final static String TEST_DB = "flights.test.data";
    private final static int FLIGHTS_AMOUNT = 20;

    @Before
    public void executedBeforeEach() {
        flightsController = new FlightsController(
                new FlightsService(
                        new FlightDao(),
                        TEST_DB
                )
        );
    }

    @Test
    public void generate_flight_base_from_and_to() {
        FlightCreator flightCreator = new FlightCreator(flightsController, FLIGHTS_AMOUNT);
        flightCreator.createFlightBase();
        System.out.println(flightsController.getAllFlights());
    }

    @Test
    public void create_flight_base_after_unlinking_it_should_create_it_again() {
        //given
        FlightCreator flightCreator = new FlightCreator(flightsController, FLIGHTS_AMOUNT);
        flightCreator.createFlightBase();
        flightsController.saveData(flightsController.getAllFlights());
        //when
        flightsController.unlinkData();
        flightsController.clearFlights();
        flightCreator.createFlightBase();
        //then
        assertEquals(FLIGHTS_AMOUNT, flightsController.getAllFlights().size());
    }
}
