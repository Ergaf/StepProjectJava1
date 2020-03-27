package ergaf.step.controllers;

import ergaf.step.dao.PassengerDao;
import ergaf.step.entities.Passenger;
import ergaf.step.services.PassengerService;
import ergaf.step.entities.User;
import ergaf.step.dao.UserDao;
import ergaf.step.services.UserService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PassengerControllerTest {

    PassengerController passengerController;
    UserController userController;

    private final static String TEST_DB = "passengers.test.data";

    @Before
    public void executedBeforeEach() {
        passengerController = new PassengerController(
                new PassengerService(
                        new PassengerDao(),
                        TEST_DB
                )
        );
        userController = new UserController(
                new UserService(
                        new UserDao()
                )
        );
    }

    @Test
    public void add_the_same_name_empty_passenger_return_the_same_passenger() {
        //given
        passengerController.addPassenger(new Passenger("Piter", "Pen"));
        passengerController.addPassenger(new Passenger("Piter", "Pen"));
        //when
        //then
        assertEquals(1, passengerController.count());
    }

    @Test
    public void add_the_same_user_passenger_return_the_same_passenger() {
        //given
        User user = userController.addUser(new User("A", "B"));
        passengerController.addPassenger(new Passenger(user));
        passengerController.addPassenger(new Passenger(user));
        //when
        //then
        assertEquals(1, passengerController.count());
    }

    @Test
    public void add_different_passengers_return_different_passengers() {
        //given
        User user = userController.addUser(new User("A", "B"));
        passengerController.addPassenger(new Passenger(user));
        passengerController.addPassenger(new Passenger("Piter", "Pen"));
        //when
        //then
        assertEquals(2, passengerController.count());
    }

    @Test
    public void add_passengers_with_and_without_user() {
        //given
        User user = userController.addUser(new User("A", "B"));
        Passenger passenger1 = passengerController.addPassenger(new Passenger("A", "B"));
        Passenger passenger2 = passengerController.addPassenger(new Passenger(user));
        //when
        //then
        assertEquals(1, passengerController.count());
        assertEquals(passenger1, passenger2);
    }

    @Test
    public void add_passengers_with_and_without_user_and_with_credentials() {
        //given
        User user = userController.addUser(new User("A", "B").setPassword("1").setLogin("1"));
        Passenger passenger2 = passengerController.addPassenger(new Passenger(user));
        Passenger passenger1 = passengerController.addPassenger(new Passenger("A", "B"));

        //when
        //then
        assertEquals(1, passengerController.count());
        assertEquals(passenger1, passenger2);
    }

    @Test
    public void save_data_generates_db_when_called() {
        //given
        User user = userController.addUser(new User("A", "B"));
        passengerController.addPassenger(new Passenger(user));
        passengerController.addPassenger(new Passenger("Piter", "Pen"));

        //when
        List<Passenger> passengers = passengerController.getAllPassengers();
        passengerController.saveData(passengers);
        //then
        passengerController.loadData();
        assertEquals(passengers, passengerController.getAllPassengers());
    }
}
