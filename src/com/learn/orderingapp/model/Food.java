package com.learn.orderingapp.model;

import java.io.Serial;

public class Food extends Dish {
    @Serial
    private static final long serialVersionUID = 2L;
    private FoodType type;
    private double weight;
    private double calories;

    public Food() {

    }

    public Food(String name, double price, String description, FoodType type, double weight, double calories) {
        super(name, price, description);
        this.type = type;
        this.weight = weight;
        this.calories = calories;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "Name: " + getName() + System.lineSeparator() +
                "Price: " + getPrice() + System.lineSeparator() +
                "Description: " + getDescription() + System.lineSeparator() +
                "Food type: " + type + System.lineSeparator() +
                "Weight: " + weight + System.lineSeparator() +
                "Calories: " + calories;
    }
}


