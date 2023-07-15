package com.cwallet.cryptowallet.controllers.responses;

import java.util.List;

public class AllCoinsFromWalletResponse extends BaseResponse{
    private List<CoinAmountResponse> coinAmountResponses;

    public List<CoinAmountResponse> getCoinAmountResponses() {
        return coinAmountResponses;
    }

    public void setCoinAmountResponses(List<CoinAmountResponse> coinAmountResponses) {
        this.coinAmountResponses = coinAmountResponses;
    }

    public AllCoinsFromWalletResponse(List<CoinAmountResponse> coinAmountResponses) {
        this.coinAmountResponses = coinAmountResponses;
    }
}
