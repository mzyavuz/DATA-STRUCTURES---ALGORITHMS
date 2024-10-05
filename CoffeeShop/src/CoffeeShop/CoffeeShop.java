package CoffeeShop;
	
public class CoffeeShop {
	private String name;
	private MenuItem[] menu;
	private String[] orders;

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
		String[] newOrders = new String[orders.length + 1]; 
		int i = 0;

		for (MenuItem item : itemList) {
			if (item.getItem().equals(itemName)) {
				for (String order : orders)
					newOrders[i++] = order;
				newOrders[i] = itemName;
				this.setOrders(newOrders);
				return "Order added!";
			}
		}
		return "This item is currently unavailable!";
	}
	
	public String fulfillOrder() {
		
		String[] orders = this.getOrders();
		int i = 0;
		
		if (orders.length == 0)
			return "All orders have been fulfilled!";
 		String item = orders[0];
		String[] newOrders = new String[orders.length-1];
		while (i < newOrders.length) {
			newOrders[i] = orders[i+1];
			i++;
		}
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
		
		for (MenuItem item : menu) {
			item.displayItemDetails();
		}
	}

}

class MenuItem {
	private String item;
	private String type;
	private double price;

	public MenuItem(String item, String type, double price) {
		this.item = item;
		this.type = type;
		this.setPrice(price); //Price can change over time
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

	FoodItem(String item, String type, double price, int calories, boolean isVegetarian) {
		super(item, type, price);
		this.calories = calories;
		this.isVegetarian = isVegetarian;
	}

	public void displayItemDetails() {
		super.displayItemDetails();
	}
}

class DrinkItem extends MenuItem {
	private String size;
	private boolean isHot;	
	
	DrinkItem(String item, String type, double price, String size, boolean isHot) {
		super(item, type, price);
		this.size = size;
		this.isHot = isHot;
	}
	
	public void displayItemDetails() {
		super.displayItemDetails();
		System.out.println("Size: " + size);
		System.out.print("Hot/Cold: ");
		System.out.println(isHot ? "Hot" : "Cold");
	}
	
}

/** 	https://edabit.com/challenge/k3pg4uMgKcDA95sqb
 * Write a class called CoffeeShop, which has three instance variables:
 * 
 * name : a string (basically, of the shop) 
 * menu : an array of items (of type MenuItem), with each item containing the item (name of the item), type
 * (whether a food or a drink) and price. orders : an empty array and seven
 * 
 * methods:
 * 
 * addOrder: adds the name of the item to the end of the orders array if it
 * exists on the menu. Otherwise, return "This item is currently unavailable!"
 * 
 * fulfillOrder: if the orders array is not empty, return "The {item} is
 * ready!". If the orders array is empty, return "All orders have been
 * fulfilled!" 
 * 
 * listOrders: returns the list of orders taken, otherwise, an empty
 * array. 
 * 
 * dueAmount: returns the total amount due for the orders taken.
 * 
 * cheapestItem: returns the name of the cheapest item on the menu. drinksOnly:
 * returns only the item names of type drink from the menu. 
 * 
 * foodOnly: returns
 * only the item names of type food from the menu. 
 * 
 * IMPORTANT: Orders are fulfilled in a FIFO (first-in, first-out) order.
 * 
 * Examples
 * 
 * tcs.addOrder("hot cocoa") ➞ "This item is currently unavailable!" //Tesha's
 * coffee shop does not sell hot cocoa tcs.addOrder("iced tea") ➞ "This item is
 * currently unavailable!" //specifying the variant of "iced tea" will help the
 * process
 * 
 * tcs.addOrder("cinnamon roll") ➞ "Order added!" tcs.addOrder("iced coffee") ➞
 * "Order added!" tcs.listOrders ➞ ["cinnamon roll", "iced coffee"] //the list
 * of all the items in the current order
 * 
 * tcs.dueAmount() ➞ 2.17
 * 
 * tcs.fulfillOrder() ➞ "The cinnamon roll is ready!" tcs.fulfillOrder() ➞ "The
 * iced coffee is ready!" tcs.fulfillOrder() ➞ "All orders have been
 * fulfilled!"; //all orders have been presumably served
 * 
 * tcs.listOrders() ➞ [] //an empty array is returned if all orders have been
 * exhausted
 * 
 * tcs.dueAmount() ➞ 0.0 //no new orders taken, expect a zero payable
 * 
 * tcs.cheapestItem() ➞ "lemonade" tcs.drinksOnly() ➞ ["orange juice",
 * "lemonade", "cranberry juice", "pineapple juice", "lemon iced tea", "vanilla
 * chai latte", "hot chocolate", "iced coffee"] tcs.foodOnly() ➞ ["tuna
 * sandwich", "ham and cheese sandwich", "bacon and egg", "steak", "hamburger",
 * "cinnamon roll"] Notes
 * 
 * Round off due amount up to two decimal places.
 */
