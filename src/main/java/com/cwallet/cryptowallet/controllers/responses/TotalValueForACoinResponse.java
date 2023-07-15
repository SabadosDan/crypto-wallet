package com.cwallet.cryptowallet.controllers.responses;

/** represents total value of a coin from a wallet */
public class TotalValueForACoinResponse extends BaseResponse{
    private String nameCoin;
    private Double totalValue;

    public String getNameCoin() {
        return nameCoin;
    }

    public void setNameCoin(String nameCoin) {
        this.nameCoin = nameCoin;
    }

    public Double getTotal_value() {
        return totalValue;
    }

    public void setTotal_value(Double totalValue) {
        this.totalValue = totalValue;
    }

    public TotalValueForACoinResponse(String nameCoin, Double totalValue) {
        this.nameCoin = nameCoin;
        this.totalValue = totalValue;
    }
}
