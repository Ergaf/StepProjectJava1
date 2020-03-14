package ergaf.step.console;

import ergaf.step.booking.BookingController;
import ergaf.step.booking.BookingService;
import ergaf.step.flight.CollectionFlightDao;
import ergaf.step.flight.Flight;
import ergaf.step.flight.FlightsController;
import ergaf.step.flight.FlightsService;

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
        fcontroller.loadDataInFile();
        if(fcontroller.giveAllFlights().size() <= 0){
            createFlightBase();
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
                    fcontroller.saveDataToFile();
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

    void createFlightBase(){
        fcontroller.saveFlightToCollection(new Flight("Киев", "Владивосток", "15:20", "1", 20));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Харьков", "00:00", "2", 11));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Одесса", "07:30", "3", 1));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Львов", "08:30", "4", 4));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Москва", "09:30", "5", 8));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Краков", "10:30", "6", 10));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Бостон", "11:30", "7", 1));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Питер", "12:30", "8", 1));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Нью-йорк", "16:30", "9", 1));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Врослав", "19:30", "10", 1));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Прага", "02:30", "11", 17));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Варшава", "08:00", "12", 28));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Вильнюс", "17:30", "13", 1));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Гамбург", "11:30", "14", 4));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Берлин", "00:30", "15", 2));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Минск", "03:30", "16", 21));
        fcontroller.saveFlightToCollection(new Flight("Киев", "Будапешт", "15:30", "17", 2));
    }
}
