package com.example.hangryapp;

public class Meal {

    public String name, restaurant;
    public String[] listOfFoodTraits;
    public double price;
    public boolean vegan, glutenFree, vegetarian, dairyFree, nutFree;

    public Meal(){

    }

    public Meal(String mealName, String restaurantName, String[] foodTraits, int numberOfFoodTraits, double foodPrice,
                boolean vegan, boolean glutenFree, boolean vegetarian, boolean dairyFree, boolean nutFree){

        this.name = mealName;
        this.restaurant = restaurantName;
        this.listOfFoodTraits = new String[numberOfFoodTraits];
        this.listOfFoodTraits = foodTraits;
        this.price = foodPrice;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.vegetarian = vegetarian;
        this.dairyFree = dairyFree;
        this.nutFree = nutFree;
    }

}
