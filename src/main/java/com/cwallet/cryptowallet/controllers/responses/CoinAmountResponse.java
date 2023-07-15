package com.cwallet.cryptowallet.controllers.responses;

import com.cwallet.cryptowallet.domain.dtos.Coin;

public class CoinAmountResponse extends BaseResponse {
    private Coin coin;
    private Double amount;

    public Coin getCoin() {
        return coin;
    }

    public void setCoinResponse(Coin coin) {
        this.coin = coin;
    }


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public CoinAmountResponse(Coin coin, Double amount) {
        this.coin = coin;
        this.amount = amount;
    }
}
