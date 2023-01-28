package com.jaironamorim.datasimulator4devs.service;


import com.jaironamorim.datasimulator4devs.model.dto.creditcard.CreditCardDto;

public interface CreditCardGenaratorService {

    CreditCardDto generateCreditCard(String label, String checkdigit);

    boolean isValidCreditCardNumber(String creditCardNumber);

}
