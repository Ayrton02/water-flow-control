package core.valueobjects;

import org.junit.Assert;
import org.junit.Test;

import java.time.temporal.ChronoUnit;

public class DateTimeTest {

    @Test()
    public void shouldReturnMillisecondsDifference() {
        DateTime first = DateTime.parse("2024-10-01T10:00:00");
        DateTime second = DateTime.parse("2024-10-01T10:00:05");

        long difference = DateTime.between(first, second, ChronoUnit.MILLIS);
        Assert.assertEquals(5000L, difference);
    }
}
