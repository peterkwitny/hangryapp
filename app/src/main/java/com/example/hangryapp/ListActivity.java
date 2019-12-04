package com.example.hangryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Meal> foodItem;
    Button buttonSwipeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

    buttonSwipeView = findViewById(R.id.buttonSwipeView);

    buttonSwipeView.setOnClickListener(this);
    }

    private void initFoodItem(){
        foodItem = new ArrayList<>();
        foodItem.add(new Meal());
        initRecyclerView();

    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(foodItem, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSwipeView){
            Intent intentBackToSwipe = new Intent(this, LandingActivity.class);
            startActivity(intentBackToSwipe);
        }

    }
}

