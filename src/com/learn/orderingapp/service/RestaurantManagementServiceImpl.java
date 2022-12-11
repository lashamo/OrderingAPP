package com.learn.orderingapp.service;

import com.learn.orderingapp.model.*;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestaurantManagementServiceImpl implements RestaurantManagementService {
    @Override
    public void addRestaurant(Restaurant restaurant, String rootDirectory) throws OrderingAppException {
        File restaurantDirectory = new File(rootDirectory + "\\" + restaurant.getName());
        if (restaurantDirectory.exists()) {
            throw new OrderingAppException(String.format("The restaurant with name %s already exists", restaurant.getName()));
        } else {
            // Create restaurant directory
            restaurantDirectory.mkdir();

            // Create orders directory
            File ordersDirectory = new File(rootDirectory + "\\" + restaurant.getName() + "\\orders");
            ordersDirectory.mkdir();

            // Save restaurant info in info.txt file
            File file = new File(rootDirectory + "\\" + restaurant.getName() + "\\info.txt");
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(restaurant);
            } catch (IOException ex) {
                throw new OrderingAppException("Error occurred while create the restaurant: " + ex.getMessage());
            }
        }
    }

    @Override
    public void addDishInTheRestaurant(Dish dish, String restaurantName, String rootDirectory) throws OrderingAppException {
        File file = new File(rootDirectory + "\\" + restaurantName + "\\info.txt");
        Restaurant restaurant;
        // Read the restaurant
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            restaurant = (Restaurant) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            throw new OrderingAppException(ex.getMessage());
        }

        // Add dish
        restaurant.getDishes().add(dish);

        // Save the restaurant
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(restaurant);
        } catch (IOException ex) {
            throw new OrderingAppException("Error occurred while save the restaurant: " + ex.getMessage());
        }
    }

    @Override
    public Order getAndDeleteFirstOrder(String restaurantName, String rootDirectory) throws OrderingAppException {
        File ordersDirectory = new File(rootDirectory + "\\" + restaurantName + "\\orders\\");
        List<Order> orders = new ArrayList<>();

        for (File orderFile : ordersDirectory.listFiles()) {
            // read order file information
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(orderFile))) {
                String id = bufferedReader.readLine();
                LocalDateTime time = LocalDateTime.parse(bufferedReader.readLine());
                Order order = new Order(id, time);
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null || line.isEmpty()) {
                        break;
                    }
                    String[] words = line.split(" ");
                    int amount = Integer.parseInt(words[0]);
                    String product = words[1];
                    OrderItem orderItem = new OrderItem(amount, product);
                    order.getOrderItems().add(orderItem);
                }
                orders.add(order);
            } catch (IOException ex) {
                throw new OrderingAppException("Error" + ex.getMessage());
            }
        }

        Order first = Collections.min(orders);
        if (first != null) {
            File firstFile = new File(rootDirectory + "\\" + restaurantName + "\\orders\\" + first.getID() + ".txt");
            firstFile.delete();
            return first;
        } else {
            throw new OrderingAppException("There is not any order");
        }
    }
}
