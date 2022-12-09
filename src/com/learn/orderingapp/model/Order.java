package com.learn.orderingapp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order implements Serializable, Comparable<Order> {
    private String ID;
    private LocalDateTime time;
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order() {
    }

    public Order(String ID, LocalDateTime time) {
        this.ID = ID;
        this.time = time;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public int compareTo(Order order) {
        return this.time.compareTo(order.getTime());
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (OrderItem item : orderItems) {
            stringBuilder.append(item.toString()).append(System.lineSeparator());
        }
      return stringBuilder.toString();
    }
}
