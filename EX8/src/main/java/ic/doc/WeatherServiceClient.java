package ic.doc;

import com.weather.Day;
import com.weather.Forecaster;
import com.weather.Region;

import java.time.DayOfWeek;

public class WeatherServiceClient implements WeatherService{

    @Override
    public int getTemperature(String location, DayOfWeek dayOfWeek) {
        int temperature = new Forecaster().forecastFor(Region.valueOf(location.toUpperCase()),
                Day.valueOf(dayOfWeek.name().toUpperCase()))
                .temperature();
        return temperature;
    }
}
