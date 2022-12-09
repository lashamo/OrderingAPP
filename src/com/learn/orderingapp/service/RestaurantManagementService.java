package com.learn.orderingapp.service;

import com.learn.orderingapp.model.Dish;
import com.learn.orderingapp.model.Order;
import com.learn.orderingapp.model.OrderingAppException;
import com.learn.orderingapp.model.Restaurant;

public interface RestaurantManagementService {

    void addRestaurant(Restaurant restaurant, String rootDirectory) throws OrderingAppException;

    void addDishInTheRestaurant(Dish dish, String restaurantName, String rootDirectory) throws OrderingAppException;

    Order getAndDeleteFirstOrder(String restaurantName, String rootDirectory) throws OrderingAppException;
}
