package ergaf.step.flight;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateGenerator {

    private long beginTime;
    private long endTime;

    public final static String DATE_PATTERN = "dd/MM/yyyy hh:mm";

    public DateGenerator(String beginTime, String endTime) throws ParseException {
        Date beginDate = new SimpleDateFormat(DATE_PATTERN).
                parse(beginTime);
        Date endDate = new SimpleDateFormat(DATE_PATTERN).
                parse(endTime);


        this.beginTime = beginDate.getTime();
        this.endTime = endDate.getTime();
    }

    public DateGenerator(int daysInterval) {
        this.beginTime = new Date().getTime();
        this.endTime = this.beginTime + daysInterval * 24 * 60 * 60 * 1000;
    }

    public static LocalDate getFlightDate(int flightYear, int flightMonth, int flightDay) {

        String flightDate = String.valueOf(flightDay) +
                '/' +
                flightMonth +
                '/' +
                flightYear +
                " 00:00";

        Date date;
        try {
            date = new SimpleDateFormat(DATE_PATTERN).
                    parse(flightDate);
        } catch (ParseException ignored) {
            throw new RuntimeException("Can't parse flight date");
        }
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public LocalDateTime getRandomFlightLocalDateTime() {
        return Instant.ofEpochMilli(
                getRandomTimeBetweenTwoDates()
        ).atZone(
                ZoneId.systemDefault()
        ).toLocalDateTime();
    }

    private long getRandomTimeBetweenTwoDates () {
        long diff = endTime - beginTime + 1;
        return beginTime + (long) (Math.random() * diff);
    }

    public LocalDateTime getBeginDateTime() {
        return Instant.ofEpochMilli(beginTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public LocalDateTime getEndDateTime() {
        return Instant.ofEpochMilli(endTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
