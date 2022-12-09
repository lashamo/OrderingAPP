import com.learn.orderingapp.model.*;
import com.learn.orderingapp.service.RestaurantClientService;
import com.learn.orderingapp.service.RestaurantClientServiceImpl;
import com.learn.orderingapp.service.RestaurantManagementService;
import com.learn.orderingapp.service.RestaurantManagementServiceImpl;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String rootDirectory;

    private static RestaurantManagementService restaurantManagementService = new RestaurantManagementServiceImpl();
    private static RestaurantClientService restClientService = new RestaurantClientServiceImpl();

    public static void main(String[] args) {
        rootDirectory = args[0];
        System.out.println("-------------Welcome to Ordering App-------------");
        while (true) {
            System.out.println("1. Management");
            System.out.println("2. Client");
            System.out.println("0. Exit");
            int mode = Integer.parseInt(scanner.nextLine());
            if (mode == 1) {
                while (true) {
                    System.out.println("1. Add restaurant");
                    System.out.println("2. Add dishes in the restaurant");
                    System.out.println("3. Show first order");
                    System.out.println("0. Exit");
                    int managementMode = Integer.parseInt(scanner.nextLine());
                    if (managementMode == 1) {
                        addRestaurant();
                    } else if (managementMode == 2) {
                        addDishInTheRestaurant();
                    } else if (managementMode == 3) {
                        showFirstOrder();
                    } else if (managementMode == 0) {
                        break;
                    }
                }
            } else if (mode == 2) {
                while (true) {
                    System.out.println("1. Show all restaurants");
                    System.out.println("2. Show all dishes in the restaurant");
                    System.out.println("3. Make order");
                    System.out.println("0. Exit");
                    int clientMode = Integer.parseInt(scanner.nextLine());
                    if (clientMode == 1) {
                        showAllRestaurants();
                    } else if (clientMode == 2) {
                        showAllDishesInTheRestaurant();
                    } else if (clientMode == 3) {
                        makeOrder();
                    } else if (clientMode == 0) {
                        break;
                    }
                }
            }
            if (mode == 0) {
                break;
            }
        }
    }

    private static void addRestaurant() {
        System.out.println("Please enter restaurant name");
        String name = scanner.nextLine();
        System.out.println("Please enter restaurant location");
        String location = scanner.nextLine();
        System.out.println("Please enter restaurant Email");
        String email = scanner.nextLine();
        Restaurant restaurant = new Restaurant(name, location, email);
        System.out.println("Restaurant created successfully");
        try {
            restaurantManagementService.addRestaurant(restaurant, rootDirectory);
        } catch (OrderingAppException ex) {
            System.out.println("<<Error>>" + ex.getMessage());
        }
    }

    private static void addDishInTheRestaurant() {
        System.out.println("Please enter restaurant name");
        String restaurantName = scanner.nextLine();
        while (true) {
            System.out.println("1. Food");
            System.out.println("2. Drink");
            System.out.println("0. Exit");
            int mode = Integer.parseInt(scanner.nextLine());
            Dish dish = null;
            if (mode == 1) {
                System.out.println("Please enter food name");
                String nameFood = scanner.nextLine();
                System.out.println("Please enter price");
                double priceFood = Double.parseDouble(scanner.nextLine());
                System.out.println("Please enter description");
                String description = scanner.nextLine();
                System.out.println("Please choose type (VEGAN, GEORGIAN, ASIAN)");
                FoodType type = FoodType.valueOf(scanner.nextLine());
                System.out.println("Please enter weight");
                double weight = Double.parseDouble(scanner.nextLine());
                System.out.println("Please enter calories");
                double calories = Double.parseDouble(scanner.nextLine());
                dish = new Food(nameFood, priceFood, description, type, weight, calories);
            } else if (mode == 2) {
                System.out.println("Please enter drink name");
                String nameDrink = scanner.nextLine();
                System.out.println("Please enter price");
                double priceDrink = Double.parseDouble(scanner.nextLine());
                System.out.println("Please enter description");
                String description = scanner.nextLine();
                System.out.println("Please enter choose type (NON_ALCOHOLIC, ALCOHOLIC)");
                DrinkType type = DrinkType.valueOf(scanner.nextLine());
                System.out.println("Please enter drinks percentage");
                int percentage = Integer.parseInt(scanner.nextLine());
                dish = new Drink(nameDrink, priceDrink, description, type, percentage);
            } else if (mode == 0) {
                break;
            }
            try {
                restaurantManagementService.addDishInTheRestaurant(dish, restaurantName, rootDirectory);
            } catch (OrderingAppException ex) {
                System.out.println("<<Error>>" + ex.getMessage());
            }
        }
    }

    private static void showAllRestaurants() {
        try {
            List<String> restaurants = restClientService.showAllRestaurant(rootDirectory);
            for (String restaurantName : restaurants) {
                System.out.println(restaurantName);
            }

        } catch (OrderingAppException ex) {
            System.out.println("<<Error>>" + ex.getMessage());
        }
    }

    private static void showAllDishesInTheRestaurant() {
        System.out.println("Please enter restaurant name");
        String name = scanner.nextLine();

        try {
            List<Dish> dishes = restClientService.listOfDish(name, rootDirectory);
            for (Dish dish : dishes) {
                if (dish instanceof Food) {
                    System.out.println("-------------Food-------------");
                    System.out.println(dish);
                    System.out.println("------------------------------");
                }
            }
            for (Dish dish : dishes) {
                if (dish instanceof Drink) {
                    System.out.println("-------------Drink-------------");
                    System.out.println(dish);
                    System.out.println("------------------------------");
                }
            }
        } catch (OrderingAppException ex) {
            System.out.println("<<Error>>" + ex.getMessage());
        }
    }

    private static void makeOrder() {
        System.out.println("Please enter restaurant name ");
        String restaurantName = scanner.nextLine();
        List<OrderItem> orderItems = new ArrayList<>();
        while (true) {
            System.out.println("1. Add dish");
            System.out.println("0. Exit");
            int makeOrderMode = Integer.parseInt(scanner.nextLine());
            if (makeOrderMode == 1) {
                System.out.println("Please enter dish name");
                String dishName = scanner.nextLine();
                System.out.println("Please enter amount");
                int amount = Integer.parseInt(scanner.nextLine());
                OrderItem orderItem = new OrderItem(amount, dishName);
                orderItems.add(orderItem);
            } else if (makeOrderMode == 0) {
                break;
            }
        }
        try {
            double summedPrice = restClientService.makeOrder(orderItems, restaurantName, rootDirectory);
            System.out.println("Order created successfully");
            System.out.println("------------Order------------");
            for (OrderItem orderItem : orderItems) {
                System.out.println(orderItem);
            }
            System.out.println("Price: " + summedPrice);
            System.out.println("----------------------------");
        } catch (OrderingAppException ex) {
            System.out.println("<<Error>>" + ex.getMessage());
        }
    }

    private  static void showFirstOrder(){
        System.out.println("Please enter restaurant name");
        String restaurantName = scanner.nextLine();
        try {
            Order order = restaurantManagementService.getAndDeleteFirstOrder(restaurantName, rootDirectory);
            for (OrderItem item : order.getOrderItems()) {
                System.out.println(item);
            }
        } catch (OrderingAppException ex) {
            System.out.println("<<Error>>" + ex.getMessage());
        }
    }
}