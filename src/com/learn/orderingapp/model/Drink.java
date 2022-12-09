package com.learn.orderingapp.model;

import java.io.Serial;

public class Drink extends Dish {
    @Serial
    private static final long serialVersionUID = 1L;
    private DrinkType type;
    private int percentage;

    public Drink() {
    }

    public Drink(String name, double price, String description, DrinkType type, int percentage) {
        super(name, price, description);
        this.type = type;
        this.percentage = percentage;
    }

    public DrinkType getType() {
        return type;
    }

    public void setType(DrinkType type) {
        this.type = type;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + System.lineSeparator() +
                "Price: " + getPrice() + System.lineSeparator() +
                "Description: " + getDescription() + System.lineSeparator() +
                "Drink type: " + type + System.lineSeparator() +
                "Percentage: " + percentage;
    }
}
