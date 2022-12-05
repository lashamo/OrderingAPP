package com.learn.orderingapp.service;

import com.learn.orderingapp.model.OrderingAppException;
import com.learn.orderingapp.model.Restaurant;

public interface RestaurantManagementService {

    void addRestaurant(Restaurant restaurant, String rootDirectory) throws OrderingAppException;
}
