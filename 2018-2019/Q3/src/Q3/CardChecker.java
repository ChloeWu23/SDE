package Q3;

import java.util.ArrayList;
import java.util.List;

public class CardChecker {
    private List<CardObserver> cardObservers = new ArrayList<>() ;

    public void validate(String s) {
        if(s.length()!=12){
            for(CardObserver obj: cardObservers){
                obj.alert(s);
            }

        }
    }

    public void addObserver(CardObserver cardObserver) {
        cardObservers.add(cardObserver);
    }

    public void removeObserver(CardObserver obj){
        cardObservers.remove(obj);
    }
}
