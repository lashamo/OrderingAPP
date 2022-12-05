package com.learn.orderingapp.service;

import com.learn.orderingapp.model.OrderingAppException;
import com.learn.orderingapp.model.Restaurant;

import java.io.*;

public class RestaurantManagementServiceImpl implements RestaurantManagementService {
    @Override
    public void addRestaurant(Restaurant restaurant, String rootDirectory) throws OrderingAppException {
        File restaurantDirectory = new File(rootDirectory + "\\" + restaurant.getName());
        if (restaurantDirectory.exists()){
            throw new OrderingAppException(String.format("The restaurant with name %s already exists", restaurant.getName()));
        } else {
            // Create restaurant directory
            restaurantDirectory.mkdir();

            // Create orders directory
            File ordersDirectory = new File(rootDirectory +"\\"+ restaurant.getName() + "\\orders");
            ordersDirectory.mkdir();

            // Save restaurant info in info.txt file
            File file = new File(rootDirectory + "\\"+restaurant.getName() +"\\info.txt");
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(restaurant);
            } catch (IOException ex) {
                throw new OrderingAppException("Error occurred while create the restaurant" + ex.getMessage());
            }
        }
    }
}
