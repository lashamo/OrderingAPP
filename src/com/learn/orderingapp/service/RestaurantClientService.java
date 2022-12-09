package com.learn.orderingapp.service;

import com.learn.orderingapp.model.Dish;
import com.learn.orderingapp.model.OrderItem;
import com.learn.orderingapp.model.OrderingAppException;

import java.util.List;

public interface RestaurantClientService {

    List<String> showAllRestaurant(String rootDirectory) throws OrderingAppException;

    List<Dish> listOfDish(String restaurantName, String rootDirectory) throws OrderingAppException;

    double makeOrder(List<OrderItem> orderItems, String restaurantName, String rootDirectory)throws OrderingAppException;
}
