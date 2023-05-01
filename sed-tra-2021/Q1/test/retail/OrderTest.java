package retail;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

import java.math.BigDecimal;
import static java.math.BigDecimal.ZERO;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class OrderTest {
    Courier royalMail = new RoyalMail();
    Courier fedex = new Fedex();
    CreditCardDetails creditCardDetails=new CreditCardDetails("1234123412341234", 111);
    Address address=new Address("180 Queens Gate, London, SW7 2AZ");

  private final Order bigOrder = new OrderBuilder().addItem(new Product("OneBook",new BigDecimal("10")))
          .addItem(new Product("OneBook",new BigDecimal("10")))
          .addItem(new Product("OneBook",new BigDecimal("10")))
          .addItem(new Product("OneBook",new BigDecimal("10")))
          .withCreditCardDetails(creditCardDetails)
          .withBillingAddressDetails(address)
          .withShippingAddressDetails(address)
          .withCourierDetail(fedex)
          .withDiscount(ZERO).build();
  private final Order smallOrder = new OrderBuilder().addItem(new Product("OneBook",new BigDecimal("10")))
          .withCreditCardDetails(creditCardDetails)
          .withBillingAddressDetails(address)
          .withShippingAddressDetails(address)
          .withCourierDetail(royalMail)
          .build();


    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    private final CardProcessor cardProcessor = context.mock(CardProcessor.class);

    @Test
    public void bigOrderIsChargedCorrectly(){
        context.checking(new Expectations(){{
            exactly(1).of(cardProcessor)
                    .charge(new BigDecimal("13.00"),creditCardDetails,address);
        }});
        smallOrder.process(cardProcessor);
    }

    @Test
    public void smallOrderIsChargedCorrectly(){
        context.checking(new Expectations(){{
            exactly(1).of(cardProcessor)
                    .charge(new BigDecimal("13.00"),creditCardDetails,address);
        }});
        bigOrder.process(cardProcessor);
    }


}
