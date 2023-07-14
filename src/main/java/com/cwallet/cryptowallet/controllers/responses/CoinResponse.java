package com.cwallet.cryptowallet.controllers.responses;

public class CoinResponse extends BaseResponse{
    private Long id;
    private String name;
    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public CoinResponse(Long id, String name, Double value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
}
