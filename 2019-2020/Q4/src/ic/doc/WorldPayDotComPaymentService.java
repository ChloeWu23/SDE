package ic.doc;

import com.worldpay.CardNumber;
import com.worldpay.CreditCardTransaction;
import com.worldpay.TransactionProcessor;

public class WorldPayDotComPaymentService implements PaymentService{
    private CardNumber cardNumber;
    @Override
    public void enterCardDetails(String cardNumber) {

        this.cardNumber = new CardNumber(cardNumber);
    }

    @Override
    public void processTransaction(int pounds, int pence) {
        new TransactionProcessor().process(new CreditCardTransaction(cardNumber, pounds, 0));
    }
}
