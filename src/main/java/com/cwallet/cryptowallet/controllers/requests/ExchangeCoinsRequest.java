package com.cwallet.cryptowallet.controllers.requests;

public class ExchangeCoinsRequest {
    private Long sellCoinId; // id of the coin you want to sell
    private Long buyCoinId; // id of the coin you want to boy
    private Long walletId;
    private Double amount; // amount of coins to buy

    public Long getSellCoinId() {
        return sellCoinId;
    }

    public void setSellCoinId(Long sellCoinId) {
        this.sellCoinId = sellCoinId;
    }

    public Long getBuyCoinId() {
        return buyCoinId;
    }

    public void setBuyCoinId(Long buyCoinId) {
        this.buyCoinId = buyCoinId;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public ExchangeCoinsRequest(Long sellCoinId, Long buyCoinId, Long walletId, Double amount) {
        this.sellCoinId = sellCoinId;
        this.buyCoinId = buyCoinId;
        this.walletId = walletId;
        this.amount = amount;
    }
}
