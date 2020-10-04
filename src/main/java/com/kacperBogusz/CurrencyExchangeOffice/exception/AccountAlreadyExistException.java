package com.kacperBogusz.CurrencyExchangeOffice.exception;

public class AccountAlreadyExistException extends Exception {
    public AccountAlreadyExistException(String message) {
        super(message);
    }
}
