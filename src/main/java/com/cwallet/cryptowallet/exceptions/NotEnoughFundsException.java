package com.cwallet.cryptowallet.exceptions;

public class NotEnoughFundsException extends RuntimeException{
    public NotEnoughFundsException(String message) {
        super(message);
    }
}
