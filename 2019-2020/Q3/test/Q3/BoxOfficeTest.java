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
  Payments payments = context.mock(Payments.class);
  WaitingList waitingList = context.mock(WaitingList.class);
  private BoxOffice boxOffice = new BoxOffice(theatre, waitingList, payments);
  // write your tests here

  @Test
  public void sallyGot4TicketsForLionKingTest(){
    context.checking(new Expectations(){{
      exactly(1).of(theatre).checkAvaiibility(LION_KING,4);will(returnValue(true));//important
      exactly(1).of(payments).makepayments(4*44,SALLY);
      exactly(1).of(theatre).confirm(LION_KING,4);
    }});

    boxOffice.bookTickets(LION_KING,4,SALLY);
  }

  @Test
  public void TomCannotGet2TicketsForHamiltonTest(){
    context.checking(new Expectations(){{
      exactly(1).of(theatre).checkAvaiibility(HAMILTON,2);will(returnValue(false));
      exactly(1).of(waitingList).add(TOM,HAMILTON,2);
    }});
    boxOffice.bookTickets(HAMILTON,2,TOM);
  }

  @Test
  public void returnTicketsTest(){
    context.checking(new Expectations(){{
      exactly(1).of(waitingList).anyoneWaiting(HAMILTON,4);
      exactly(1).of(payments).makepayments(2*80,TOM);
    }});

    boxOffice.returnTickets(HAMILTON,4);
    boxOffice.bookTickets(2,TOM,HAMILTON);
  }

}
