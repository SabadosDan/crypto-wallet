package com.cwallet.cryptowallet.controllers.responses;

import com.cwallet.cryptowallet.domain.dtos.Coin;

public class TransactionResponse extends BaseResponse{
    private Long id;
    private String date;
    private Coin coin;
    private Double amount;
    private Double totalValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public TransactionResponse(Long id, String date, Coin coin, Double amount, Double totalValue) {
        this.id = id;
        this.date = date;
        this.coin = coin;
        this.amount = amount;
        this.totalValue = totalValue;
    }
}
