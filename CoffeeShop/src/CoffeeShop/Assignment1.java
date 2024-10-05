package CoffeeShop;

public class Assignment1 {

	public static void main(String[] args) {
		MenuItem coffee = new DrinkItem("hot coffee", "drink", 100, "large", true);
		MenuItem water = new DrinkItem("water", "drink", 40, "500 ml", false);
		MenuItem salad = new FoodItem("salad", "food", 140, 251, true);
		MenuItem[] menu = new MenuItem[3];
		menu[0] = coffee;
		menu[1] = water;
		menu[2] = salad;
		CoffeeShop coffeeWorld = new CoffeeShop("coffee world", menu, new String[] {});
		System.out.println(coffeeWorld.addOrder("water"));
		System.out.println(coffeeWorld.addOrder("wter"));
		System.out.println(coffeeWorld.fulfillOrder());
		System.out.println(coffeeWorld.fulfillOrder());
		System.out.println(coffeeWorld.addOrder("water"));
		System.out.println(coffeeWorld.addOrder("hot coffee"));
		System.out.println(coffeeWorld.dueAmount());
		System.out.println(coffeeWorld.cheapestItem());
		System.out.println(coffeeWorld.foodOnly()[0]);
		System.out.println(coffeeWorld.drinksOnly()[0]);
		coffeeWorld.displayMenu();
		
	}
}