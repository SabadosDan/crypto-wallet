package com.cwallet.cryptowallet.controllers.responses;

import com.cwallet.cryptowallet.domain.dtos.CoinAmount;

public class BuyCoinResponse extends BaseResponse{
    private CoinAmountResponse coinAmountResponse;
    private String status;
    private String message;

    public CoinAmountResponse getCoinAmountResponse() {
        return coinAmountResponse;
    }

    public void setCoinAmountResponse(CoinAmountResponse coinAmountResponse) {
        this.coinAmountResponse = coinAmountResponse;
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

    public BuyCoinResponse(CoinAmountResponse coinAmountResponse, String status, String message) {
        this.coinAmountResponse = coinAmountResponse;
        this.status = status;
        this.message = message;
    }
}
