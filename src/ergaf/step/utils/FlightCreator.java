package ergaf.step.utils;

import ergaf.step.controllers.FlightsController;
import ergaf.step.entities.Flight;

import java.util.ArrayList;
import java.util.Random;

public class FlightCreator {

    private static final String START_DATE = "27/03/2020 00:00";
    private static final String END_DATE = "05/04/2020 00:00";
    private String from;
    private int i = 0;
    Random random = new Random();

    private int flightAmount;

    FlightsController flightsController;
    DateGenerator dateGenerator;

    public static String DEPARTURE = "Kyiv";

    public FlightCreator(FlightsController flightsController, int flightAmount) {
        this.flightsController = flightsController;
        this.flightAmount = flightAmount;
    }

    public void createFlightBase(){
        i=0;
        dateGenerator = new DateGenerator(2);

        ArrayList<String> toList = new ArrayList<>();
        int fiftyPercent = (int) (flightAmount*0.5);

        while (i < fiftyPercent) {
            String to = getNextDestination();
            addFlight(i+1, DEPARTURE, to);
            toList.add(to);
            i++;
        }
        toList.forEach(s -> {
            i++;
            String to = getNextDestination();
            if (s.equals(to)) {
                to = getNextDestination();
            }
            addFlight(i, s, to);
        });
    }

    private void addFlight(int i, String from, String to) {
        flightsController.addFlight(
                new Flight(
                        from,
                        to,
                        dateGenerator.getRandomFlightLocalDateTime(),
                        random.nextInt(100)
                )
        );
    }

    private String getNextDestination() {
        return Destination.values()[
                random.nextInt(Destination.values().length)
                ].name();
    }
}
