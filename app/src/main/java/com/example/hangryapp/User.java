package com.example.hangryapp;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {

    public String email, password;
    public Boolean vegan, glutenfree, vegetarian, dairyfree, nutfree;
    //public ArrayList<Meal> savedmeals;
    public ArrayList<Meal> savedmeals = new ArrayList<Meal>();
    public double priceMax;
    public String cusinePreference1, cusinePreference2;

    public User() {
    }

    public User(String email, String password, Boolean vegan, Boolean glutenfree, Boolean vegetarian, Boolean dairyfree, Boolean nutfree, ArrayList<Meal> savedmeals, double priceMax, String pref1, String pref2) {
        this.email = email;
        this.password = password;
        this.vegan = vegan;
        this.glutenfree = glutenfree;
        this.vegetarian = vegetarian;
        this.dairyfree = dairyfree;
        this.nutfree = nutfree;
        this.savedmeals = savedmeals;
        this.priceMax = priceMax;
        this.cusinePreference1 = pref1;
        this.cusinePreference2 = pref2;
        //this.savedmeals = savedmeals;
       //this.atemeals = atemeals; ArrayList<String> savedmeals, ArrayList<String> atemeals
    }
}
