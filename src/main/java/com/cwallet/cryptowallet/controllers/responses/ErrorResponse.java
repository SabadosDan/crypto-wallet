package com.cwallet.cryptowallet.controllers.responses;

public class ErrorResponse extends BaseResponse {
    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ErrorResponse(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
