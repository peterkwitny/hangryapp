package com.example.hangryapp;

import java.util.ArrayList;

public class User {

    String email, password;
    Boolean vegan, glutenfree, vegetarian, dairyfree, nutfree;
    ArrayList<String> savedmeals, atemeals;

    public User() {
    }

    public User(String email, String password, Boolean vegan, Boolean glutenfree, Boolean vegetarian, Boolean dairyfree, Boolean nutfree, ArrayList<String> savedmeals, ArrayList<String> atemeals) {
        this.email = email;
        this.password = password;
        this.vegan = vegan;
        this.glutenfree = glutenfree;
        this.vegetarian = vegetarian;
        this.dairyfree = dairyfree;
        this.nutfree = nutfree;
        this.savedmeals = savedmeals;
        this.atemeals = atemeals;
    }
}
