package ic.doc;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class ShoppingBasketTest {
    @Rule
    public JUnitRuleMockery context = new JUnitRuleMockery();
    PaymentService paymentService = context.mock(PaymentService.class);
    ShoppingBasket shoppingBasket = new ShoppingBasket(paymentService);

    @Test
    public void canCheckOutTest(){
        Item item = new Item("book",10);
        shoppingBasket.addItem(item);

        context.checking(new Expectations(){{
            exactly(1).of(paymentService).process(10,0);
        }});


        shoppingBasket.checkout();
    }

    @Test
    public void canAddItemsTest(){
        Item item1 = new Item("book",10);
        Item item2 = new Item("apple",3);
        shoppingBasket.addItem(item1);
        shoppingBasket.addItem(item2);
    }

    @Test
    public void canEnterCreditCardTest(){
        context.checking(new Expectations(){{
            exactly(1).of(paymentService).enterCardDetails("12341234");
        }});
        shoppingBasket.enterCardDetails("12341234");
    }
}
