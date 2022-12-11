package com.learn.orderingapp.service;

import com.learn.orderingapp.model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RestaurantClientServiceImpl implements RestaurantClientService {

    @Override
    public List<String> showAllRestaurant(String rootDirectory) throws OrderingAppException {
        // create list of restaurant
        List<String> restaurants = new ArrayList<>();
        //create rootDirectory
        File root = new File(rootDirectory);
        for (File restDirectory : root.listFiles()) {
            if (restDirectory.isDirectory()) {
                restaurants.add(restDirectory.getName());
            }
        }
        return restaurants;
    }

    @Override
    public List<Dish> listOfDish(String restaurantName, String rootDirectory) throws OrderingAppException {
        File file = new File(rootDirectory + "\\" + restaurantName + "\\info.txt");
        // read of computer file info.txt
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Restaurant restaurant = (Restaurant) inputStream.readObject();
            return restaurant.getDishes();
        } catch (IOException | ClassNotFoundException ex) {
            throw new OrderingAppException("error" + ex.getMessage());
        }
    }

    @Override
    public double makeOrder(List<OrderItem> orderItems, String restaurantName, String rootDirectory) throws OrderingAppException {
        // create random value of unicodeID
        String uniqueID = UUID.randomUUID().toString();
        // create localDateTIme for now
        LocalDateTime now = LocalDateTime.now();

        double summedPrice = 0;
        // create list of dishes
        List<Dish> dishes = listOfDish(restaurantName, rootDirectory);
        for (OrderItem item : orderItems){
            Dish dish = null;
            for (int i = 0; i < dishes.size(); i++) {
                if (item.getProduct().equals(dishes.get(i).getName())) {
                    dish = dishes.get(i);
                    break;
                }
            }
            if (dish != null) {
                summedPrice += dish.getPrice() * item.getAmount();
            } else {
                throw new OrderingAppException("There is not any dish with name " + item.getProduct());
            }
        }

        File file = new File(rootDirectory + "\\" + restaurantName + "\\orders\\" + uniqueID + ".txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
            bufferedWriter.write(uniqueID + System.lineSeparator());
            bufferedWriter.write(now + System.lineSeparator());
            for (OrderItem item : orderItems) {
                bufferedWriter.write(item.getAmount() + " " + item.getProduct() + System.lineSeparator());
            }
        } catch (IOException ex) {
            throw new OrderingAppException("<<Error>>" + ex.getMessage());
        }

        return summedPrice;
    }
}
