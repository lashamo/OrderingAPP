package com.learn.orderingapp.model;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private int amount;
    private String product;

    public OrderItem() {
    }

    public OrderItem(int amount, String product) {
        this.amount = amount;
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return product + " - " + amount;
    }
}
