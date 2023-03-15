package ic.doc;

import org.junit.Test;

import java.time.DayOfWeek;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class WeatherServiceClientTest {
    @Test
    public void canRetrieveTemperatureData() {
        WeatherService weatherservice = new WeatherServiceClient();
        int temp = weatherservice.getTemperature("London", DayOfWeek.FRIDAY);
        assertThat(temp,is(greaterThan(-10)));
    }
}
