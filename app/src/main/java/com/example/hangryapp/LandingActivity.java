package com.example.hangryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textViewFoodName,textViewRestaurant,textViewPrice, textViewRestriction, textViewRestriction2, textViewRestriction3, textViewRestriction4, textViewRestriction5;
    Button buttonNope, buttonSave, buttonFilter, buttonListView;
    ImageView imageViewFood;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        textViewFoodName = findViewById(R.id.textViewFoodName);
        textViewRestaurant = findViewById(R.id.textViewRestaurant);
        textViewPrice = findViewById(R.id.textViewPrice);
        textViewRestriction = findViewById(R.id.textViewRestriction);
        textViewRestriction2 = findViewById(R.id.textViewRestriction2);
        textViewRestriction3 = findViewById(R.id.textViewRestriction3);
        textViewRestriction4 = findViewById(R.id.textViewRestriction4);
        textViewRestriction5 = findViewById(R.id.textViewRestriction5);
        buttonNope = findViewById(R.id.buttonNope);
        buttonSave = findViewById(R.id.buttonSave);
        buttonFilter = findViewById(R.id.buttonFilter);
        buttonListView = findViewById(R.id.buttonListView);
        imageViewFood = findViewById(R.id.imageViewFood);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Meal");


        myRef.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Meal foundMeal = dataSnapshot.getValue(Meal.class);
                String findName = foundMeal.name;
                String findRestaurant = foundMeal.restaurant;
                String findPrice = foundMeal.price;
                Boolean findVegan = foundMeal.vegan;
                Boolean findVegetarian = foundMeal.vegetarian;
                Boolean findGF = foundMeal.glutenFree;
                Boolean findDF = foundMeal.dairyFree;
                Boolean findNF = foundMeal.nutFree;

                textViewFoodName.setText(findName);
                textViewRestaurant.setText(findRestaurant);
                textViewPrice.setText(findPrice);
                if (findVegan == true) {
                    textViewRestriction.setText("Vegan");
                }
                if (findVegetarian == true) {
                    textViewRestriction2.setText("Vegetarian");
                }
                if (findGF == true) {
                    textViewRestriction3.setText("Gluten Free");
                }
                if (findDF == true) {
                    textViewRestriction4.setText("Dairy Free");
                }
                if (findNF == true) {
                    textViewRestriction5.setText("Nut Free");
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemLikedFood){
            Toast.makeText(this, "You are already in Portal, you fool!", Toast.LENGTH_SHORT).show();
        }else if(item.getItemId() == R.id.itemAddFood){
            Intent profileIntent = new Intent(this, AddFoodActivity.class);
            startActivity(profileIntent);

        }else if(item.getItemId() == R.id.itemFAQ){
            Intent settingsIntent = new Intent(this, FAQActivity.class);
            startActivity(settingsIntent);
        }else if(item.getItemId() == R.id.itemSettings){
            Intent logoutIntent = new Intent(this, SettingsActivity.class);
            startActivity(logoutIntent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Meal");

        if(view == buttonFilter){
            Intent intent = new Intent(this, FilterActivity.class);
            startActivity(intent);
        } else if( view == buttonSave){



        } else if(view == buttonNope){

        }else if(view == buttonListView){
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);

        }

    }

}
