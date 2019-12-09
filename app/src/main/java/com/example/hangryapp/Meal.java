package com.example.hangryapp;

import java.io.File;

public class Meal {

    public String name, restaurant;
    public String mealtime, cuisine;
    public String price;
    public boolean vegan, glutenFree, vegetarian, dairyFree, nutFree;

    public String foodName;
    public File foodPic;


    public Meal() {
    }

    public Meal(String name, String restaurant, String mealtime, String cuisine, String price, boolean vegan, boolean glutenFree, boolean vegetarian, boolean dairyFree, boolean nutFree, String foodName, File foodPic) {
        this.name = name;
        this.restaurant = restaurant;
        this.mealtime = mealtime;
        this.cuisine = cuisine;
        this.price = price;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
        this.vegetarian = vegetarian;
        this.dairyFree = dairyFree;
        this.nutFree = nutFree;
        this.foodName = foodName;
        this.foodPic = foodPic;
    }
}
