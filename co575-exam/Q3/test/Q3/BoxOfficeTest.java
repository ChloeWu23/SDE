package Q3;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class BoxOfficeTest {

  static final Show LION_KING =
      new Show("The Lion King", 44.00);

  static final Show HAMILTON =
      new Show("Hamilton", 80.00);

  static final Customer SALLY = new Customer("Sally Davies");
  static final Customer TOM = new Customer("Thomas Williams");

  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  Theatre theatre = context.mock(Theatre.class);
  Payment payment = context.mock(Payment.class);
  WaitingList waitingList = context.mock(WaitingList.class);
  BoxOffice boxOffice = new BoxOffice(theatre, payment, waitingList);

  // write your tests here
  @Test
  public void getTicketsWhenEnoughLeftTest(){
    context.checking(new Expectations(){{
      exactly(1).of(theatre).checkAvailable(LION_KING,4);
      //debug here:this line means set expectations for the above when it is called
      will(returnValue(true));
      exactly(1).of(payment).pay(44*4,SALLY);
      exactly(1).of(theatre).confirm(LION_KING,4);
    }});
    boxOffice.bookTickets(LION_KING,4,SALLY);
  }

  @Test
  public void canNotGetTicketsWhenNotEnoughLeftTest(){
    context.checking(new Expectations(){{
      exactly(1).of(theatre).checkAvailable(HAMILTON,2);
      //debug here:this line means set expectations for the above when it is called
      will(returnValue(false));
      exactly(1).of(waitingList).add(TOM,HAMILTON,2);

    }});
    boxOffice.bookTickets(HAMILTON,2,TOM);
  }


  @Test
  public void returnTicketsTest(){
    context.checking(
        new Expectations() {
          {
            exactly(1).of(waitingList).anyoneWaiting(HAMILTON, 4);
            // debug here:this line means set expectations for the above when it is called
            will(returnValue(true));
            exactly(1).of(payment).pay(HAMILTON.price()*2,TOM);
          }
        });
    boxOffice.returnTickets(HAMILTON,4);
    boxOffice.bookTickets(TOM,HAMILTON,2);
  }




}
