package com.example.hangryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Meal> foodItem; //Creating a new arraylist of food item for the adapter to display
    private RecyclerViewAdapter recyclerViewAdapter;
    Button buttonSwipeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        foodItem = new ArrayList<Meal>();
       // foodItem.add(new Meal("Pizza", "Joes", "Dinner", "American", "5", false, false, false, false, false, ""));

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Meal");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    foodItem.add(snapshot.getValue(Meal.class));
                }


                RecyclerView recyclerView = findViewById(R.id.recycler_view);
                recyclerViewAdapter = new RecyclerViewAdapter(foodItem, ListActivity.this);
                recyclerView.setAdapter(recyclerViewAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ListActivity.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        buttonSwipeView = findViewById(R.id.buttonSwipeView);
        buttonSwipeView.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view == buttonSwipeView){
            Intent intentBackToSwipe = new Intent(this, LandingActivity.class);
            startActivity(intentBackToSwipe);
        }

    }
}

