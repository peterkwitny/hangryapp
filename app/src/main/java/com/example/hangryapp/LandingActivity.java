package com.example.hangryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class LandingActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textViewFoodName,textViewRestaurant,textViewPrice, textViewRestriction, textViewRestriction2, textViewRestriction3, textViewRestriction4, textViewRestriction5;
    Button buttonNope, buttonSave, buttonFilter, buttonListView;
    ImageView imageViewFood;
    int currentDisplay;
    Meal currentSavedMeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StorageReference mStorageRef;
        mStorageRef = FirebaseStorage.getInstance().getReference();

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

        buttonListView.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        buttonFilter.setOnClickListener(this);
        buttonNope.setOnClickListener(this);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Meal");


        myRef.orderByKey().limitToFirst(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                currentDisplay = 1;

                Meal foundMeal = dataSnapshot.getValue(Meal.class);
                currentSavedMeal = foundMeal;

                String findName = foundMeal.name;
                String findRestaurant = foundMeal.restaurant;
                String findPrice = foundMeal.price;
                String picReference = foundMeal.picReference;
                Boolean findVegan = foundMeal.vegan;
                Boolean findVegetarian = foundMeal.vegetarian;
                Boolean findGF = foundMeal.glutenFree;
                Boolean findDF = foundMeal.dairyFree;
                Boolean findNF = foundMeal.nutFree;

                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference photoReference = storageReference;





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
            Intent likedFoodIntent = new Intent(this, LikedFoodActivity.class);
            startActivity(likedFoodIntent);

            Toast.makeText(this, "You are already in the main page", Toast.LENGTH_SHORT).show();
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
        final DatabaseReference myRef2 = database.getReference("User");




        if(view == buttonFilter){
            Intent filterIntent = new Intent(this, FilterActivity.class);
            startActivity(filterIntent);
        } else if( view == buttonSave){
            currentDisplay = currentDisplay + 1;
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String findEmail = user.getEmail();


            myRef2.orderByChild("email").equalTo(findEmail).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    //User foundUser = dataSnapshot.getValue(User.class);
                    //ArrayList<Meal> findSaveMeals = foundUser.savedmeals;
                    String editKey = dataSnapshot.getKey();

                    //Meal currentMeal = dataSnapshot.getValue(Meal.class);
                        /*
                        String name = thisMeal.name;
                        String restaurant = thisMeal.restaurant;
                        String mealtime = thisMeal.mealtime;
                        String cuisine = thisMeal.cuisine;
                        String price = thisMeal.price;
                        Boolean vegan = thisMeal.vegan;
                        Boolean glutenFree = thisMeal.glutenFree;
                        Boolean vegetarian = thisMeal.vegetarian;
                        Boolean dairyFree = thisMeal.dairyFree;
                        Boolean nutFree = thisMeal.nutFree;

                         */



                    //findSaveMeals.add(currentMeal);

                    myRef2.child(editKey).child("savedmeals").push().setValue(currentSavedMeal);

                        /* add to likedfood
                        new LikedFoodActivity().textViewFoodItem1.setText(thisMeal.name);
                        new LikedFoodActivity().textViewRest1.setText(thisMeal.restaurant);
                        new LikedFoodActivity().textViewPrice1.setText(thisMeal.price);
                         */



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



            myRef.orderByKey().limitToFirst(currentDisplay).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {




                    Meal currentMeal = dataSnapshot.getValue(Meal.class);


                    String findName = currentMeal.name;
                    String findRestaurant = currentMeal.restaurant;
                    String findPrice = currentMeal.price;
                    Boolean findVegan = currentMeal.vegan;
                    Boolean findVegetarian = currentMeal.vegetarian;
                    Boolean findGF = currentMeal.glutenFree;
                    Boolean findDF = currentMeal.dairyFree;
                    Boolean findNF = currentMeal.nutFree;


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

                    Toast.makeText(LandingActivity.this, "Added to Liked Foods!", Toast.LENGTH_SHORT).show();
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

        } else if(view == buttonNope){
            currentDisplay = currentDisplay + 1;
            myRef.orderByKey().limitToLast(currentDisplay).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            myRef.orderByKey().limitToFirst(currentDisplay).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {



                    Meal newMeal = dataSnapshot.getValue(Meal.class);



                    String findName = newMeal.name;
                    String findRestaurant = newMeal.restaurant;
                    String findPrice = newMeal.price;
                    Boolean findVegan = newMeal.vegan;
                    Boolean findVegetarian = newMeal.vegetarian;
                    Boolean findGF = newMeal.glutenFree;
                    Boolean findDF = newMeal.dairyFree;
                    Boolean findNF = newMeal.nutFree;


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

        }else if(view == buttonListView){
            Intent listViewIntent = new Intent(this, ListActivity.class);
            startActivity(listViewIntent);

        }

    }

}