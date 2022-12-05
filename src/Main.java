import com.learn.orderingapp.model.OrderingAppException;
import com.learn.orderingapp.model.Restaurant;
import com.learn.orderingapp.service.RestaurantManagementService;
import com.learn.orderingapp.service.RestaurantManagementServiceImpl;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static String rootDirectory;

    private static RestaurantManagementService restaurantService = new RestaurantManagementServiceImpl();

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
                    System.out.println("2. Add dish in the restaurant");
                    System.out.println("3. Show all orders in the restaurant");
                    System.out.println("0. Exit");
                    mode = Integer.parseInt(scanner.nextLine());
                    if (mode == 1) {
                        addRestaurant();
                    } else if (mode == 2) {

                    } else if (mode == 3) {

                    } else if (mode == 0) {
                        break;
                    }
                }

            } else if (mode == 2) {
                while (true) {
                    System.out.println("1. Show all restaurants");
                    System.out.println("2. Show all dishes in the restaurant");
                    System.out.println("3. Make order");
                    System.out.println("0. Exit");
                    mode = Integer.parseInt(scanner.nextLine());
                    if (mode == 1) {

                    } else if (mode == 2) {

                    } else if (mode == 3) {

                    } else if (mode == 0) {
                        break;
                    }
                }

            }
            if (mode == 0){
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
            restaurantService.addRestaurant(restaurant, rootDirectory);
        } catch (OrderingAppException ex) {
            System.out.println("<<Error>>" + ex.getMessage());
        }
    }
}