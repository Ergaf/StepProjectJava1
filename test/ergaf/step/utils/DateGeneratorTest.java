package ergaf.step.utils;

import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class DateGeneratorTest {

    @Test
    public void getDateString() {
        try {
            for (int x = 0; x < 7; x++) {
                DateGenerator dateGenerator = new DateGenerator(
                        "31/12/1998 10:00",
                        "31/12/2020 10:00"
                );
                assertNotNull(dateGenerator.getRandomFlightLocalDateTime());
            }
        } catch (ParseException ignored) {

        }
    }

    @Test
    public void time_generated_adds_plus_interval_days_from_now() {
        DateGenerator dateGenerator = new DateGenerator(2);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        assertEquals(dtf.format(LocalDateTime.now()), dtf.format(dateGenerator.getBeginDateTime()));
        assertEquals(dtf.format(LocalDateTime.now().plusDays(2)), dtf.format(dateGenerator.getEndDateTime()));
    }

}
