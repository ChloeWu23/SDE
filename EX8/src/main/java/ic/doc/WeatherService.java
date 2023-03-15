package ic.doc;

import com.weather.Day;
import com.weather.Forecast;
import com.weather.Region;

import java.time.DayOfWeek;

public interface WeatherService {
    public int getTemperature(String location, DayOfWeek dayOfWeek);
}
