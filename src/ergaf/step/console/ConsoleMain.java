package ergaf.step.console;

import ergaf.step.controllers.FlightsController;
import ergaf.step.entities.Booking;
import ergaf.step.entities.Passenger;
import ergaf.step.menu.Menu;
import ergaf.step.menu.SubMenu;
import ergaf.step.controllers.BookingController;
import ergaf.step.utils.input.Input;
import ergaf.step.utils.*;
import ergaf.step.controllers.PassengerController;
import ergaf.step.entities.User;
import ergaf.step.controllers.UserController;

import java.time.LocalDateTime;
import java.util.List;

public class ConsoleMain implements ConsoleInterface{

    Input subInput;
    FlightsController fcontroller;
    BookingController bookingController;
    FlightCreator flightCreator;
    UserController userController;
    PassengerController passengerController;


    public ConsoleMain(
            FlightsController flightsController,
            BookingController bookingController,
            UserController userController,
            PassengerController passengerController,
            Input subInput,
            FlightCreator flightCreator
    ) {
        this.fcontroller = flightsController;
        this.bookingController = bookingController;
        this.userController = userController;
        this.passengerController = passengerController;
        this.subInput = subInput;
        this.flightCreator = flightCreator;
    }

    public String startConsole()
    {
        String userIn = subInput.getRawStringInput();

        switch (userIn) {
            case "1":
                fcontroller.displayFlights(
                        fcontroller.getFlightsByRange(
                                LocalDateTime.now(),
                                LocalDateTime.now().plusDays(1)
                        )
                );
                break;
            case "2":
                System.out.print("Введите айди рейса: ");
                int id = subInput.getIntInput();
                System.out.println(fcontroller.getFlightById(id).prettyFormat());
                subInput.getRawStringInput();
                break;
            case "3":
                System.out.print("Поиск и бронировка рейса. ");
                System.out.println(SubMenu.SUB_MENU);

                ConsoleSearchAndBooking consoleSearchAndBooking =
                        new ConsoleSearchAndBooking(
                                subInput,
                                fcontroller,
                                userController,
                                bookingController,
                                passengerController
                        );
                String command = consoleSearchAndBooking.startConsole();

                while (!command.equals("0")) { // 0 -> exit
                    System.out.println("\nPlease enter number from sub menu: ");
                    command = consoleSearchAndBooking.startConsole();
                }

                break;
            case "4":
                System.out.println("Все бронирования:");
                bookingController.displayFlights(
                        bookingController.getBookingsByUser(
                                userController.getCurrentUser()
                        )
                );
                System.out.println("Введите id бронирования для отмени:");
                int cancelBookingId = subInput.getIntInput();

                if (bookingController.getBookingById(cancelBookingId) != null) {
                    bookingController.cancelBookingById(cancelBookingId);
                    System.out.println("Бронирование отменено");
                } else {
                    System.out.println("Бронирование не найдено");
                }
                subInput.getRawStringInput();
                break;
            case "5":
                System.out.println("Укажите имя пасажира");
                String firstName = subInput.getRawStringInput();
                System.out.println("Укажите фамилию пасажира");
                String lastName  = subInput.getRawStringInput();

                Passenger passenger = passengerController.getPassengerByFirstNameAndLastName(firstName, lastName);

                if (passenger == null) {
                    System.out.println("Пасажир не найден");
                } else {
                    bookingController.displayFlights(
                            bookingController.getBookingsByPassenger(passenger)
                    );
                }
                break;
            case "6":
                System.out.println("Мои бронирования:");

                List<Booking> bookingList = bookingController.getBookingsByUser(userController.getCurrentUser());
                if (bookingList.size() > 0) {
                    bookingController.displayFlights(bookingList);
                } else {
                    System.out.println("Бронирования не найдени");
                }

                break;
            case "7":
                fcontroller.unlinkData();
                fcontroller.clearFlights();

                bookingController.unlinkData();
                bookingController.clearBookings();

                userController.unlinkData();
                userController.clearUsers();
                userController.setCurrentUser(null);

                passengerController.unlinkData();
                passengerController.clearPassengers();

                flightCreator.createFlightBase();
                System.out.println("Данние перегенерировани успешно.");
                break;
            case "8":
            case "9":
                fcontroller.saveData(fcontroller.getAllFlights());
                userController.setCurrentUser(null);
                break;
            default:
                if (userController.getCurrentUser() != null) {
                    System.out.println(
                            "\t\t\t\t\t\t\t\t\t\t\t\t\t\tАвторизований пользователь: '" +
                                    userController.getCurrentUser().prettyFormat() +
                                    "'"
                    );
                }
                System.out.println(Menu.MENU);
                break;

        }
        return userIn;
    }

}
