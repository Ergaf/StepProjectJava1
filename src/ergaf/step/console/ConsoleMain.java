package ergaf.step.console;

import ergaf.step.booking.BookingController;
import ergaf.step.booking.BookingService;
import ergaf.step.flight.*;

import java.util.Scanner;

public class ConsoleMain {
    Scanner in = new Scanner(System.in);

    FlightsController fcontroller;
    BookingController bookingController;


    {
        System.out.println("создался екземпляр "+this.getClass().getSimpleName());
    }


    public ConsoleMain(FlightsController flightsController, BookingController bookingController) {
        this.fcontroller = flightsController;
        this.bookingController = bookingController;
    }

    public void startConsole()
    {
        boolean running = true;

        fcontroller.loadData();
        if(fcontroller.getAllFlights().size() <= 0){
            FlightCreator.createFlightBase(fcontroller);
        }

        while (running){
            printMainMenu();
            String userIn = in.nextLine().toLowerCase();
            switch (userIn) {
                case "1":
                    fcontroller.displayAllFlights();
                    ifMenu();
                    break;
                case "2":
                    System.out.print("Введите айди рейса: ");
                    fcontroller.giveFlightForId(in.nextLine()).prettyFormat();
                    ifMenu();
                    break;
                case "3":
                    System.out.println("место назначения:");
                    ifMenu();
                    break;
                case "4":
                    System.out.print("4: ");
                    ifMenu();
                    break;
                case "5":
                    System.out.print("5: ");
                    ifMenu();
                    break;
                case "6":
                    fcontroller.saveData(fcontroller.getAllFlights());
                    running = false;
                    break;

            }

        }
    }

    void printMainMenu(){
        System.out.println("1 - Онайн-табло.");
        System.out.println("2 - Посмотреть информацию о рейсе.");
        System.out.println("3 - Поиск и бронировка рейса.");
        System.out.println("4 - Отменить бронирование.");
        System.out.println("5 - Мои рейсы.");
        System.out.println("6 - Выход.");
        System.out.print("введите номер действия: ");
    }

    void ifMenu(){
        System.out.print("нажмите ввод что бы вернуться в главное меню: ");
        in.nextLine();
    }
}
