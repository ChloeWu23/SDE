package ic.doc;

import java.util.HashMap;
import java.util.Map;

public class ShoppingBasket {

  private final Map<Item, Integer> items = new HashMap<>();
  //Debug here:adaptor pattern
  private PaymentService paymentService;

  public ShoppingBasket(PaymentService paymentService) {
    this.paymentService = paymentService;
  }
  //private CardNumber cardNumber;

  public void addItem(Item item) {
    if (items.containsKey(item)) {
      items.put(item, items.get(item) + 1);
    } else {
      items.put(item, 1);
    }
  }
//debug here
  public void enterCardDetails(String cardNumber) {
    paymentService.enterCardDetails(cardNumber);
  }


  public void checkout() {
    int totalPounds = 0;
    int totalItems = 0;
    for (Item item : items.keySet()) {
      Integer quantity = items.get(item);
      totalItems = totalItems + quantity;
      totalPounds = totalPounds + quantity * item.priceInPounds();
    }

    if (totalItems > 3) {
      totalPounds = Math.min(totalPounds, totalPounds - 5);
    }
    paymentService.process(totalPounds,0);
    //new TransactionProcessor().process(new CreditCardTransaction(cardNumber, totalPounds, 0));
  }
}