package retail;

public class OrderProcessor {

  public void prepareForDelivery(Order order) {

    Customer customer = order.getCustomer();
    String postCode = customer.getAddress().getPostCode();

    Label label = new Label();
    // ...
    label.addLine(postCode);
  }
}
