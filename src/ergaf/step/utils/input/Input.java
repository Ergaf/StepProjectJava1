package ergaf.step.utils.input;

import java.time.Year;
import java.time.YearMonth;
import java.util.Scanner;

public class Input {

    private Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public int getIntInput() {

        int input = getInt();
        while (input <= 0) {
            input = getInt();
        }

        return input;
    }

    public String getRawStringInput() {

        return scanner.nextLine();
    }

    public String getStringInput() {

        String value = scanner.nextLine();
        while (value.isEmpty()) {
            value = scanner.nextLine();
        }

        return value;
    }

    private int getInt() {

        int input;
        try {
            input = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
//            scanner.nextLine();
            return 0;
        }
        return input;
    }

    public int getIntInputYear(int defaultYear) {
        int input = getInt();
        if (input == 0) {
            input = defaultYear;
        }
        while (input < 2020 || input > Year.now().getValue()) {
            System.out.println("Year must be more than 2020 and less than " + Year.now().getValue());
            input = getInt();
        }

        return input;
    }

    public int getIntInputFlight(int count) {

        int input = getInt();
        while (input < 0 || input > count) {
            System.out.println("ID рейса не існує");
            input = getInt();
        }

        return input;
    }

    public int getIntInputMonth(int defaultMonth) {

        int input = getInt();
        if (input == 0) {
            input = defaultMonth;
        }
        while (input <= 0 || input > 12) {
            System.out.println("Month must be more than 0 and less than 13");
            input = getInt();
        }

        return input;
    }

    public int getIntInputDay(int year, int month, int defaultDay) {

        YearMonth yearMonthObject = YearMonth.of(year, month);

        int input = getInt();
        if (input == 0) {
            input = defaultDay;
        }
        while (input <= 0 || input > yearMonthObject.lengthOfMonth()) {
            System.out.println("Month must be more than 0 and less than or equals " + yearMonthObject.lengthOfMonth());
            input = getInt();
        }

        return input;
    }

    public int getIntSubInput() {

        int input = getInt();
        while (input < 0 || input > 1) {
            System.out.println("Please choose wisely between 0 and 1");
            input = getInt();
        }

        return input;
    }
}
