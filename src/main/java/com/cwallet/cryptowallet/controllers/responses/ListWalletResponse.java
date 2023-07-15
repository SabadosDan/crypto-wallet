package com.cwallet.cryptowallet.controllers.responses;

import java.util.List;

public class ListWalletResponse extends BaseResponse{
    private List<WalletResponse> walletResponses;

    public List<WalletResponse> getWalletResponses() {
        return walletResponses;
    }

    public void setWalletResponses(List<WalletResponse> walletResponses) {
        this.walletResponses = walletResponses;
    }

    public ListWalletResponse(List<WalletResponse> walletResponses) {
        this.walletResponses = walletResponses;
    }
}
