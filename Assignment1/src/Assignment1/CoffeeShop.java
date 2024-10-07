package Assignment1;


public class CoffeeShop {
   private String name;
   private MenuItem[] menu;
   private String[] orders;


   CoffeeShop() {
      this.name = "null";
      this.menu = new MenuItem[0];
      this.orders = new String[0];
   }
  
   CoffeeShop(String name, MenuItem[] menu, String[] orders) {
      this.setName(name);
      this.setMenu(menu);
      this.setOrders(orders);
   }


   public void setName(String name) {
      this.name = name;
   }


   public void setMenu(MenuItem[] menu) {
      this.menu = menu;
   }


   public void setOrders(String[] orders) {
      this.orders = orders;
   }


   public String getName() {
      return name;
   }


   public MenuItem[] getMenu() {
      return menu;
   }


   public String[] getOrders() {
      return orders;
   }


   public String addOrder(String itemName) {
      MenuItem[] itemList = this.getMenu();
      String[] orders = this.getOrders();


      for (MenuItem item : itemList) {
         if (item.getItem().equals(itemName)) {
            String[] newOrders = new String[orders.length+1];
            for (int i = 0; i < orders.length ; i++)
               newOrders[i] = orders[i];             
            newOrders[newOrders.length-1] = itemName;
            this.setOrders(newOrders);
            return "Order added!";
         }
      }
      return "This item is currently unavailable!";
   }


   public String fulfillOrder() {
      String[] orders = this.getOrders();


      if (orders.length == 0)
         return "All orders have been fulfilled!";
      String item = orders[0];
      String[] newOrders = new String[orders.length-1];
      for (int i = 0; i < newOrders.length; i++)
         newOrders[i] = orders[i + 1];
      this.setOrders(newOrders);
      return "The " + item + " is ready!";
   }


   public String[] listOrders() {
      return this.getOrders();
   }


   public double dueAmount() {
      MenuItem[] menu = this.getMenu();
      String[] orders = this.getOrders();
      double totalAmount = 0;


      for (MenuItem item : menu) {
         for (String order : orders) {
            if (item.getItem().equals(order)) {
               totalAmount += item.getPrice();
            }
         }
      }
      return totalAmount;
   }


   public String cheapestItem() {
      MenuItem[] menu = this.getMenu();
      MenuItem cheapestItem = menu[0];


      for (MenuItem item : menu) {
         if (item.getPrice() < cheapestItem.getPrice())
            cheapestItem = item;
      }
      return cheapestItem.getItem();
   }


   public String[] drinksOnly() {
      MenuItem[] menu = this.getMenu();
      int i = 0;


      for (MenuItem item : menu) {
         if (item.getType() == "drink")
            i++;
      }


      String[] drinks = new String[i];
      i = 0;
      for (MenuItem item : menu) {
         if (item.getType() == "drink")
            drinks[i++] = item.getItem();
      }
      return drinks;
   }


   public String[] foodOnly() {


      MenuItem[] menu = this.getMenu();
      int i = 0;


      for (MenuItem item : menu) {
         if (item.getType() == "food")
            i++;
      }


      String[] food = new String[i];
      i = 0;
      for (MenuItem item : menu) {
         if (item.getType() == "food")
            food[i++] = item.getItem();
      }
      return food;
   }


   public void displayMenu() {
      MenuItem[] menu = this.getMenu();


      if (menu.length == 0)
      {
         System.out.println("There is no available menu!");
         return ;
      }
      System.out.println("---------- MENU ------------");
      for (MenuItem item : menu) {
         item.displayItemDetails();
         System.out.println("----------------------------");
      }
   }
  
   public static void displayTotalVegFood() {
      System.out.println("There are " + FoodItem.getTotalVegFood() + " vegeterian food here!");
   }


}


class MenuItem {
   private String item;
   private String type;
   private double price;


   public MenuItem(String item, String type, double price) {
      this.item = item;
      this.type = type;
      this.setPrice(price); // Price can change over time
   }


   public void setPrice(double price) {
      this.price = price;
   }


   public String getItem() {
      return item;
   }


   public String getType() {
      return type;
   }


   public double getPrice() {
      return price;
   }


   public void displayItemDetails() {
      System.out.println("Name: " + item);
      System.out.println("Type: " + type);
      System.out.println("Price: " + price + "$");
   }
  
}


class FoodItem extends MenuItem {
   private int calories;
   private boolean isVegetarian;
   private static int totalVegItem = 0;


   FoodItem(String item, double price, int calories, boolean isVegetarian) {
      super(item, "food", price);
      this.calories = calories;
      this.isVegetarian = isVegetarian;
      if (isVegetarian)
         totalVegItem++;
   }


   public void displayItemDetails() {
      super.displayItemDetails();
      System.out.println("Calories: " + calories + " kcal");
      if (isVegetarian)
         System.out.println("This food is vegeterian");
   }
  
   public static int getTotalVegFood() {
      return totalVegItem;
   }
}


class DrinkItem extends MenuItem {
   private String size;
   private boolean isHot;


   DrinkItem(String item, double price, String size, boolean isHot) {
      super(item, "drink", price);
      this.size = size;
      this.isHot = isHot;
   }


   public void displayItemDetails() {
      super.displayItemDetails();
      System.out.println("Size: " + size);
      System.out.print("Hot/Cold: ");
      System.out.println(isHot ? "Hot" : "Cold");
   }


   public static DrinkItem convertToDrinkItem(MenuItem menu, String size, boolean isHot) {
      return new DrinkItem(menu.getItem(), menu.getPrice(), size, isHot);
   }
}
