package com.cwallet.cryptowallet.controllers.responses;

public class ExchangeCoinsResponse extends BaseResponse{
    private CoinAmountResponse buyCoinAmountResponse;
    private CoinAmountResponse sellCoinAmountResponse;
    private String status;
    private String message;

    public ExchangeCoinsResponse(CoinAmountResponse buyCoinAmountResponse, CoinAmountResponse sellCoinAmountResponse,
                                 String status, String message) {
        this.buyCoinAmountResponse = buyCoinAmountResponse;
        this.sellCoinAmountResponse = sellCoinAmountResponse;
        this.status = status;
        this.message = message;
    }

    public CoinAmountResponse getBuyCoinAmountResponse() {
        return buyCoinAmountResponse;
    }

    public void setBuyCoinAmountResponse(CoinAmountResponse buyCoinAmountResponse) {
        this.buyCoinAmountResponse = buyCoinAmountResponse;
    }

    public CoinAmountResponse getSellCoinAmountResponse() {
        return sellCoinAmountResponse;
    }

    public void setSellCoinAmountResponse(CoinAmountResponse sellCoinAmountResponse) {
        this.sellCoinAmountResponse = sellCoinAmountResponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
