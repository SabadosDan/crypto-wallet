package com.cwallet.cryptowallet.domain.dtos;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "coins")
public class Coin extends BaseEntity{
    @Column
    private String name;

    @Column
    private Double value;

    public Coin(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public Coin() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
}
