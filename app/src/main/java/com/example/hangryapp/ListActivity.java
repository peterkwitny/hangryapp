/*package com.example.hangryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Meal> foodItem; //Creating a new arraylist of food item for the adapter to display
    private RecyclerViewAdapter recyclerViewAdapter;
    Button buttonSwipeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        foodItem = new ArrayList<Meal>();
        getFoodItem();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerViewAdapter = new RecyclerViewAdapter(foodItem, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonSwipeView = findViewById(R.id.buttonSwipeView);
        buttonSwipeView.setOnClickListener(this);

    }

    private void getFoodItem(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference contextRef = database.getReference("Meal");

        contextRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    Meal food = child.getValue(Meal.class);
                    String findName = food.name;
                    String findRestaurant = food.restaurant;
                    String findPrice = food.price;

                    foodItem.add(food);

                }
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
    }


    @Override
    public void onClick(View view) {
        if (view == buttonSwipeView){
            Intent intentBackToSwipe = new Intent(this, LandingActivity.class);
            startActivity(intentBackToSwipe);
        }

    }
}
*/
