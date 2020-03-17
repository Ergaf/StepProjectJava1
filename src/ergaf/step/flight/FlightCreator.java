package ergaf.step.flight;

public class FlightCreator {

    public static void createFlightBase(FlightsController fcontroller){

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
