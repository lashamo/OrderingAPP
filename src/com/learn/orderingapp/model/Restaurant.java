package com.learn.orderingapp.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Restaurant implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    private String name;
    private String location;
    private String email;
    private List<Dish> dishes = new ArrayList<>();

    public Restaurant() {
    }

    public Restaurant(String name, String location, String email) {
        this.name = name;
        this.location = location;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
