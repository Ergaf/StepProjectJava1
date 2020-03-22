package ergaf.step.console;

import ergaf.step.menu.Menu;
import ergaf.step.menu.SubMenu;
import ergaf.step.booking.BookingController;
import ergaf.step.input.Input;
import ergaf.step.flight.*;
import ergaf.step.user.User;
import ergaf.step.user.UserController;

public class ConsoleMain implements ConsoleInterface{

    Input subInput;
    FlightsController fcontroller;
    BookingController bookingController;
    FlightCreator flightCreator;
    UserController userController;


    public ConsoleMain(
            FlightsController flightsController,
            BookingController bookingController,
            UserController userController,
            Input subInput,
            FlightCreator flightCreator
    ) {
        this.fcontroller = flightsController;
        this.bookingController = bookingController;
        this.userController = userController;
        this.subInput = subInput;
        this.flightCreator = flightCreator;
    }

    public String startConsole()
    {
        String userIn = subInput.getRawStringInput();

        switch (userIn) {
            case "1":
                fcontroller.displayAllFlights();
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
                                bookingController
                        );
                String command = consoleSearchAndBooking.startConsole();

                while (!command.equals("0")) { // 0 -> exit
                    System.out.println("\nPlease enter number from sub menu: ");
                    command = consoleSearchAndBooking.startConsole();
                }

                break;
            case "4":
                System.out.println("Все бронирования пасажиров:");
                bookingController.displayBookings(bookingController.getAllBookings());
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

                User user = userController.getUserByFirstNameAndLastName(firstName, lastName);

                if (user == null) {
                    System.out.println("Пасажир не найден");
                } else {
                    bookingController.displayFlights(
                            bookingController.getBookingsByUser(user)
                    );
                }
                break;
            case "6":
                System.out.println("Мои бронирования:");

                bookingController.displayFlights(
                        bookingController.getBookingsByUser(userController.getCurrentUser())
                );
                break;
            case "7":
                fcontroller.unlinkData();
                fcontroller.clearFlights();

                bookingController.unlinkData();
                bookingController.clearBookings();

                userController.unlinkData();
                userController.clearUsers();
                userController.setCurrentUser(null);

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
