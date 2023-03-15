package ic.doc;

import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.Map;

public class CacheWeatherService implements WeatherService {
    private  WeatherService downstream;
    private Map<Pair<String,DayOfWeek>,Integer> cache;

   /*
    public CacheWeatherService(WeatherService downstream) {
        //this.downstream = downstream;
        this(downstream,1000);
    }
*/
    public CacheWeatherService(WeatherService downstream,int capacity){
        this.downstream = downstream;
        this.cache = new LinkedHashMap<>(){
            @Override
            public boolean removeEldestEntry(Map.Entry<Pair<String, DayOfWeek>, Integer> old){
                return size() > capacity;
            }
        };
    }



    @Override
    public int getTemperature(String location, DayOfWeek dayOfWeek) {
     Pair<String,DayOfWeek> key = new Pair<>(location,dayOfWeek);
     if(!cache.containsKey(key)) {
         cache.put(key,downstream.getTemperature(location,dayOfWeek));
     }
     return cache.get(key);
    }
}


