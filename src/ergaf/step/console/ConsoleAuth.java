package ergaf.step.console;

import ergaf.step.booking.BookingController;
import ergaf.step.flight.FlightCreator;
import ergaf.step.flight.FlightsController;
import ergaf.step.input.Input;
import ergaf.step.menu.AuthMenu;
import ergaf.step.menu.Menu;
import ergaf.step.passenger.PassengerController;
import ergaf.step.user.User;
import ergaf.step.user.UserController;

public class ConsoleAuth implements ConsoleInterface {

    private Input input;
    private UserController userController;
    private FlightsController flightsController;
    private BookingController bookingController;
    private PassengerController passengerController;
    private FlightCreator flightCreator;

    public ConsoleAuth(
            Input input,
            UserController userController,
            FlightsController flightsController,
            BookingController bookingController,
            PassengerController passengerController,
            FlightCreator flightCreator) {
        this.input = input;
        this.userController = userController;
        this.flightsController = flightsController;
        this.bookingController = bookingController;
        this.passengerController = passengerController;
        this.flightCreator = flightCreator;
    }

    public String startConsole()
    {
        String userIn = input.getRawStringInput();
        switch (userIn) {
            case "1":
                System.out.println("Создание аккаунта:");

                System.out.println("Введите имя:");
                String firstName = input.getRawStringInput();
                System.out.println("Введите фамилию:");
                String lastName = input.getRawStringInput();

                User userCreated = userController.getUserByLoginAndPassword(firstName, lastName);
                if (userCreated != null) {
                    System.out.println("Пользователь с таким именем и фамилией уже есть в базе");
                    break;
                }
                System.out.println("Введите логин:");
                String loginCreated = input.getRawStringInput();
                System.out.println("Введите пароль:");
                String passwordCreated = input.getRawStringInput();

                userController.addUser(
                        new User(firstName, lastName).
                                setLogin(loginCreated).
                                setPassword(passwordCreated)
                );
                System.out.println("Аккаунт добавлен");
                break;
            case "2":
                System.out.println("Введите логин:");
                String loginAuth = input.getRawStringInput();
                System.out.println("Введите пароль:");
                String passwordAuth = input.getRawStringInput();

                User userAuth = userController.getUserByLoginAndPassword(loginAuth, passwordAuth);
                if (userAuth != null) {
                    System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t\tАвторизований пользователь: '" + userAuth.prettyFormat() + "'");
                    userController.setCurrentUser(userAuth);

                    ConsoleMain console = new ConsoleMain(
                            flightsController,
                            bookingController,
                            userController,
                            passengerController,
                            input,
                            flightCreator
                    );

                    System.out.println(Menu.MENU);
                    System.out.println("Please enter number from menu: ");
                    String command = console.startConsole();

                    while (!command.equals("9") && userController.getCurrentUser() != null) { // 9 -> exit
                        System.out.println("\nPlease enter number from menu: ");
                        command = console.startConsole();
                    }
                } else {
                    System.out.println(userController.getAllUsers());
                    System.out.println("Пользователь не найден");
                }
                break;
            case "3":
                userController.saveData(userController.getAllUsers());
                System.out.println("Exit");
                break;
            default:
                System.out.print("Приложение для бронировки авиабилетов.");
                System.out.println(AuthMenu.AUTH_MENU);
                break;
        }
        return userIn;
    }

}
