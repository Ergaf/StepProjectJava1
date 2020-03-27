package ergaf.step.console;

import ergaf.step.booking.Booking;
import ergaf.step.booking.BookingController;
import ergaf.step.flight.DateGenerator;
import ergaf.step.flight.Flight;
import ergaf.step.flight.FlightCreator;
import ergaf.step.flight.FlightsController;
import ergaf.step.input.Input;
import ergaf.step.menu.Menu;
import ergaf.step.menu.SubMenu;
import ergaf.step.passenger.Passenger;
import ergaf.step.passenger.PassengerController;
import ergaf.step.user.User;
import ergaf.step.user.UserController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ConsoleSearchAndBooking implements ConsoleInterface{

    private Input input;
    private FlightsController flightsController;
    private UserController userController;
    private BookingController bookingController;
    private PassengerController passengerController;

    public ConsoleSearchAndBooking(
            Input input,
            FlightsController flightsController,
            UserController userController,
            BookingController bookingController,
            PassengerController passengerController
    ) {
        this.input = input;
        this.flightsController = flightsController;
        this.userController = userController;
        this.bookingController = bookingController;
        this.passengerController = passengerController;
    }

    public String startConsole()
    {
        String userIn = input.getRawStringInput();
        switch (userIn) {
            case "0":
                userController.saveData(userController.getAllUsers());
                bookingController.saveData(bookingController.getAllBookings());
                passengerController.saveData(passengerController.getAllPassengers());

                System.out.println(Menu.MENU);
                break;
            case "1":

                System.out.println("Поиск и бронировка рейса. -> место отправления (Kyiv):");
                String departure = input.getRawStringInput();
                if (departure.isEmpty()) {
                    departure = "Kyiv";
                }
                System.out.println("Поиск и бронировка рейса. -> место назначения (Germany):");
                String destination = input.getRawStringInput();
                if (destination.isEmpty()) {
                    destination = "Germany";
                }
                System.out.println("Поиск и бронировка рейса. -> дата (год) ("+ LocalDateTime.now().getYear()+"):");
                int flightYear = input.getIntInputYear(LocalDateTime.now().getYear());
                System.out.println("Поиск и бронировка рейса. -> дата (месяц) ("+ LocalDateTime.now().getMonth().getValue()+"):");
                int flightMonth = input.getIntInputMonth(LocalDateTime.now().getMonth().getValue());
                System.out.println("Поиск и бронировка рейса. -> дата (день) ("+ LocalDateTime.now().getDayOfMonth()+"):");
                int flightDay = input.getIntInputDay(flightYear, flightMonth, LocalDateTime.now().getDayOfMonth());
                System.out.println("Поиск и бронировка рейса. -> количество человек (сколько необходимо купить билетов):");
                int ticketsAmount = input.getIntInput();

                LocalDate desiredFlightDate = DateGenerator.getFlightDate(
                        flightYear,
                        flightMonth,
                        flightDay
                );
                List<Flight> flights = flightsController.searchFlights(
                        departure,
                        destination,
                        desiredFlightDate,
                        ticketsAmount
                );
                if (flights.size() == 0) {
                    System.out.println("No flights found according to your data");
                    input.getRawStringInput();
                    break;
                }

                flightsController.displayFlights(flights);
                System.out.println("Укажите id рейса:");
                int flightId = input.getIntInputFlight(flightsController.count());
                Flight flight = flightsController.getFlightById(flightId);
                if (flight == null || flight.getFreePlaces() < ticketsAmount) {
                    System.out.println("Flight has not been found or there is not enough seats");
                    input.getRawStringInput();
                    break;
                }

                System.out.println("Укажите имя и фамилию для "+ticketsAmount+ " пасажиров:");
                String firstName;
                String lastName;
                for (int i = 0; i < ticketsAmount; i++) {
                    System.out.println("Укажите имя для пасажира #" + (i+1));
                    firstName = input.getRawStringInput();
                    System.out.println("Укажите фамилию для пасажира #" + (i+1));
                    lastName  = input.getRawStringInput();
                    Passenger passenger = passengerController.addPassenger(new Passenger(firstName, lastName));
                    bookingController.addBooking(new Booking(flight, passenger));
                    System.out.println("Бронировка сохранена");
                }
                break;
            default:
                System.out.print("Поиск и бронировка рейса. ");
                System.out.println(SubMenu.SUB_MENU);
                break;
        }
        return userIn;
    }

}
