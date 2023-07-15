package com.cwallet.cryptowallet.domain.dtos;

import javax.persistence.*;

@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity{
    @Column
    private String date;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    private Coin coin;

    @Column
    private Double amount;

    @Column(name = "total_value")
    private Double totalValue;

    public Transaction() {

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

    public Transaction(String date, Coin coin, Double amount, Double totalValue) {
        this.date = date;
        this.coin = coin;
        this.amount = amount;
        this.totalValue = totalValue;
    }
}
