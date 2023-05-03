package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AuctionManagerTest {
  @Rule
  public JUnitRuleMockery context = new JUnitRuleMockery();
  Item item = context.mock(Item.class);
  Seller seller = context.mock(Seller.class);
  Payment payment = context.mock(Payment.class);
  Bidder bidder = context.mock(Bidder.class);
  Dispatch dispatch = context.mock(Dispatch.class);
  private AuctionManager manager = new AuctionManager(payment, bidder, dispatch, seller, item);
  @Before
  public void setUp(){
    manager.startAuction(item,seller);
  }

  @Test
  public void initialBidSuccessfulTest() {
    context.checking(new Expectations(){{
      exactly(1).of(payment).charge(10,"Alice");
      exactly(1).of(bidder).giveResult();
    }});

  this.manager.getBid(10,"Alice");
  }


  @Test
  public void lowerBidFailTest() {
    context.checking(new Expectations(){{
      oneOf(payment).charge(10,"Alice");
      exactly(0).of(payment).charge(5,"Carole");
      ignoring(bidder);
    }});
    this.manager.getBid(10,"Alice");
    this.manager.getBid(5,"Carole");

  }

  @Test
  public void bidSuccessWhenExcessHighestBid() {
    //manager.highest = 10;
    context.checking(new Expectations(){{
      ignoring(payment).charge(10,"Alice");
      ignoring(payment).charge(5,"Carole");
      exactly(1).of(payment).charge(20,"David");
      ignoring(bidder);
    }});
    this.manager.getBid(10,"Alice");
    this.manager.getBid(5,"Carole");
    manager.getBid(20,"David");
  }

  @Test
  public void winAuctionWithHighestBidTest() {
    context.checking(new Expectations() {{
      ignoring(payment).charge(with(any(Integer.class)), with(any(String.class)));
      exactly(1).of(payment).pay(20, seller);
      exactly(1).of(payment).pay(10,"Alice");
      exactly(1).of(dispatch).patchItem(item, "David");
      ignoring(bidder);
    }});

    manager.getBid(10, "Alice");
    manager.getBid(5, "Carole");
    manager.getBid(20, "David");
    manager.endAuction();
  }




}
