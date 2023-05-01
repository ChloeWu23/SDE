package ic.doc;

public interface PaymentService {
    void enterCardDetails(String cardNumber);
    void processTransaction(int pounds, int pence);
}
