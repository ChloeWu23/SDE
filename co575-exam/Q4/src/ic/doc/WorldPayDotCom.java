package ic.doc;

import com.worldpay.CardNumber;
import com.worldpay.CreditCardTransaction;
import com.worldpay.TransactionProcessor;

public class WorldPayDotCom implements PaymentService{
    private CardNumber cardNumber;
    //private TransactionProcessor transactionProcessor;
    //private CreditCardTransaction creditCardTransaction ;
    public void enterCardDetails(String cardNumber) {
        this.cardNumber = new CardNumber(cardNumber);
    }
    public void process(int num1,int num2){
        TransactionProcessor transactionProcessor = new TransactionProcessor();
        CreditCardTransaction creditCardTransaction = new CreditCardTransaction(cardNumber,num1,num2);
        transactionProcessor.process(creditCardTransaction);
        //new TransactionProcessor().process(new CreditCardTransaction(cardNumber, totalPounds, 0));
    }

}
