package Assignment1;

import java.util.Scanner;


public class Assignment1 {


   private static Scanner input;

public static void main(String[] args) {
      MenuItem coffee = new DrinkItem("hot coffee", 100, "large", true);
      MenuItem water = new DrinkItem("water", 40, "500 ml", false);
      MenuItem salad = new FoodItem("salad", 140, 51, true);
      MenuItem[] menu = new MenuItem[3];
      menu[0] = coffee;
      menu[1] = water;
      menu[2] = salad;
     
      CoffeeShop coffeeShop = new CoffeeShop("CoffeeWorld", menu, new String[] {});
      System.out.println("Welcome to " + coffeeShop.getName() + "!");
     
      input = new Scanner(System.in);
     
      coffeeShop.displayMenu();
      CoffeeShop.displayTotalVegFood(); 
      System.out.println("");
     
      String order;
      while (true) {
           System.out.print("Enter an item to order (or type 'done' to finish): ");
           order = input.nextLine();


           if (order.equalsIgnoreCase("done")) {
               break;
           }


           String message = coffeeShop.addOrder(order);
           System.out.println(message);
       }
     
      System.out.println("Your charge is : " + coffeeShop.dueAmount() + "$");
     
      while (coffeeShop.getOrders().length != 0)
         System.out.println(coffeeShop.fulfillOrder());


   }


}

