package com.cwallet.cryptowallet.controllers.responses;

import java.util.List;

/** represents total value of a wallet */
public class WalletValueResponse extends BaseResponse{
    private List<TotalValueForACoinResponse> totalValuesForEachCoin;
    private Double totalValueOfWallet;

    public List<TotalValueForACoinResponse> getTotalValuesForEachCoin() {
        return totalValuesForEachCoin;
    }

    public void setTotalValuesForEachCoin(List<TotalValueForACoinResponse> totalValuesForEachCoin) {
        this.totalValuesForEachCoin = totalValuesForEachCoin;
    }

    public Double getTotalValueOfWallet() {
        return totalValueOfWallet;
    }

    public void setTotalValueOfWallet(Double totalValueOfWallet) {
        this.totalValueOfWallet = totalValueOfWallet;
    }

    public WalletValueResponse(List<TotalValueForACoinResponse> totalValuesForEachCoin, Double totalValueOfWallet) {
        this.totalValuesForEachCoin = totalValuesForEachCoin;
        this.totalValueOfWallet = totalValueOfWallet;
    }
}
