package ergaf.step.booking;

import java.time.Year;
import java.time.YearMonth;
import java.util.InputMismatchException;
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
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            return 0;
        }
        return input;
    }

}
