package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class ShoppingBasketTest {

    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    private PaymentService paymentService = context.mock(PaymentService.class);
    private ShoppingBasket shoppingBasket = new ShoppingBasket(paymentService);

    @Test
    public void canAddItemsTest(){
        shoppingBasket.addItem((new Item("Laptop",500)));
    }
    @Test
    public void canEnterCardDetailTest(){
        context.checking(new Expectations(){{
            exactly(1).of(paymentService).enterCardDetails("1234");
        }});
        shoppingBasket.enterCardDetails("1234");
    }

    @Test
    public void canCheckoutTest(){
        context.checking(new Expectations(){{
            //oneOf(paymentService).enterCardDetails("1234");
            //oneOf(paymentService).processTransaction(500,0);
            ignoring(paymentService).enterCardDetails(with(any(String.class)));
            exactly(1).of(paymentService).processTransaction(500,0);
        }});
        shoppingBasket.addItem(new Item("Laptop",500));
        shoppingBasket.enterCardDetails("1234");
        shoppingBasket.checkout();
    }

    /*
    Note that when we have multiple calls, more than one call will invoke interface, so we use oneof(interface)
    instead of exactly(1).....

    * */
}
