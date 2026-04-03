package com.wilsonmanaog.automation.model;

public class CreditCardInfo {
    private final CreditCardType cardType;
    private final String cardholderName;
    private final String cardNumber;
    private final String expirationMonth;
    private final String expirationYear;
    private final String cardCode;

    public CreditCardInfo(CreditCardType cardType, String cardholderName, String cardNumber, String expirationMonth, String expirationYear, String cardCode) {
        this.cardType = cardType;
        this.cardholderName = cardholderName;
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.cardCode = cardCode;
    }

    public CreditCardType getCardType() {
        return cardType;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationMonth() {
        return expirationMonth;
    }

    public String getExpirationYear() {
        return expirationYear;
    }

    public String getCardCode() {
        return cardCode;
    }
}
