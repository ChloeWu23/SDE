package Q3;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class CardCheckerTest {
  // implement your tests here
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
     CardObserver cardObserver1 = context.mock(CardObserver.class,"observer1"); //debug:mock twice give different name
     CardObserver cardObserver2 = context.mock(CardObserver.class,"observer2");
    CardChecker cardchecker = new CardChecker();
    //Important: @Before means that all test run this method before testing

  @Before
    public void Initial(){
      cardchecker.addObserver(cardObserver1);
      cardchecker.addObserver(cardObserver2);
    }

    @Test
    public void ValidateFirstCard(){
      context.checking(new Expectations(){{
       never(cardObserver1);//means cardObserver1 never get callednever(cardObserver2);
        never(cardObserver2);
      }});
      cardchecker.validate("111122223333");
    }

    @Test
  public void InvalidateSecondCard(){
    context.checking(new Expectations(){{
      exactly(1).of(cardObserver1).alert("1111");
      exactly(1).of(cardObserver2).alert("1111");
    }});
    cardchecker.validate("1111");
    }

    @Test
  public void InvalidateThirdCardWhenOnlyOneObserverTest(){
    context.checking(new Expectations(){{
      exactly(1).of(cardObserver1).alert("2222");
    }});
    cardchecker.removeObserver(cardObserver2);
    cardchecker.validate("2222");
    }

}
