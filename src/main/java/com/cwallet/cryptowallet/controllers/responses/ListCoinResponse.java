package com.cwallet.cryptowallet.controllers.responses;

import java.util.List;

public class ListCoinResponse extends BaseResponse{
    private List<CoinResponse> coinResponses;

    public List<CoinResponse> getCoinResponses() {
        return coinResponses;
    }

    public void setCoinResponses(List<CoinResponse> coinResponses) {
        this.coinResponses = coinResponses;
    }

    public ListCoinResponse(List<CoinResponse> coinResponses) {
        this.coinResponses = coinResponses;
    }
}
