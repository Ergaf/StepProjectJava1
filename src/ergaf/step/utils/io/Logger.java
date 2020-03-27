package ergaf.step.utils.io;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static String filename = "application.log";

    public static void info(String message) {

        write(filename, getFormattedLogDateTime() + " [DEBUG] " + message);
    }

    public static void error(String message) {

        write(filename, getFormattedLogDateTime() + " [ERROR] " + message);
    }

    private static String getFormattedLogDateTime() {
        LocalDateTime now = LocalDateTime.now();
        String DATE_PATTERN = "dd/MM/yyyy HH:mm";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return now.format(formatter);
    }

    public static void write(String fileName, String text) {

        try {
            PrintWriter printWriter = new PrintWriter(
                    new PrintStream(
                            new FileOutputStream(getBasePath() + fileName, true)
                    )
            );
            printWriter.write("\n" + text);
            printWriter.close();
        } catch(IOException e) {
            error("io exception");
            throw new RuntimeException(e);
        }
    }

    private static String getBasePath() {
        return System.getProperty("user.dir") + "/storage/";
    }
}
