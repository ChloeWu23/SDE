package com.worldpay;

// DO NOT CHANGE THE CODE IN THIS FILE

public class CardNumber {

  public CardNumber(String cardnumber) {
    if (cardnumber.length() != 16) {
      throw new IllegalArgumentException("card number must be 16 digits");
    }

    // imagine more code here - not implemented for the exam

  }
}


/*
Answer for 4A:
1.Adaptor Pattern
2.create worldpaydotcompaymentservice class and paymentservice interface, modify shoppingbasket so all methods
from third party are taken inside adaptor.
3.Architectural Style is Hexagonal Architecture
 */
