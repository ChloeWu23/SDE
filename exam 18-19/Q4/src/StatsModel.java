import java.util.ArrayList;
import java.util.List;

public class StatsModel{
    private final List<Integer> numbers = new ArrayList<>();
    private int max=0;
    private double mean=0;
    private List<Updatable> observers = new ArrayList<>();

    public void pressonebutton(int n) {
        numbers.add(n);
        max = Math.max(max, n);
        mean = numbers.stream().mapToInt(val -> val).average().orElse(0.0);
        notifyObservers(max,mean);
    }
//debug: Don't forget this funciton
    public void addObservers(Updatable obj){
        observers.add(obj);
    }

    private void notifyObservers(int max, double mean) {
        for (Updatable obj:observers){
            obj.update(max,mean);
        }
    }
}
