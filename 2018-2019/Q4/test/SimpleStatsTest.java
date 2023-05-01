import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class SimpleStatsTest {
    //Note: Test Model, Mock Interface
    //private StatsModel model= new StatsModel();
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    Updatable simpleStats = context.mock(Updatable.class);
//debug here: forget add observer
    @Test
    public void pressbuttonChangedValue(){
        StatsModel model= new StatsModel();
        model.addObservers(simpleStats);
        context.checking(new Expectations(){{
            exactly(1).of(simpleStats).update(2,2.0);
        }});

        model.pressonebutton(2);

    }

}
