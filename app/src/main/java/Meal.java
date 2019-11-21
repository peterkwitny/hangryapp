public class Meal {

    public String name, restaurant;
    public String mealtime, cuisine;
    public double price;
    public boolean vegan, glutenFree, vegetarian, dairyFree, nutFree;

    public Meal() {
    }

    public Meal(String name, String restaurant, String trait1, String trait2, double price, boolean vegan, boolean glutenFree, boolean vegetarian, boolean dairyFree, boolean nutFree) {
        this.name = name;
        this.restaurant = restaurant;
        this.mealtime = trait1;
        this.cuisine = trait2;
        this.price = price;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.vegetarian = vegetarian;
        this.dairyFree = dairyFree;
        this.nutFree = nutFree;
    }
}
