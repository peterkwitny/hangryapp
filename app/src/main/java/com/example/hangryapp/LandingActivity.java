package com.example.hangryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class LandingActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textViewFoodName,textViewRestaurant,textViewPrice, textViewRestriction, textViewRestriction2, textViewRestriction3, textViewRestriction4, textViewRestriction5;
    Button buttonNope, buttonSave, buttonFilter, buttonListView;
    ImageView imageViewFood;
    int currentDisplay = 1;
    Meal currentSavedMeal;
    StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* final StorageReference mStorageRef; */
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


    myRef.orderByKey().limitToFirst(currentDisplay).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {


                Meal foundMeal = snapshot.getValue(Meal.class);
                currentSavedMeal = foundMeal;

                String findName = foundMeal.name;
                String findRestaurant = foundMeal.restaurant;
                String findPrice = foundMeal.price;
                final String picReference = foundMeal.picReference;
                Boolean findVegan = foundMeal.vegan;
                Boolean findVegetarian = foundMeal.vegetarian;
                Boolean findGF = foundMeal.glutenFree;
                Boolean findDF = foundMeal.dairyFree;
                Boolean findNF = foundMeal.nutFree;



                StorageReference picRef = mStorageRef.child(picReference);
                final File localFile;
                try {
                    localFile = File.createTempFile("image", "jpg");

                        picRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(localFile));
                                    imageViewFood.setImageBitmap(bitmap);
                                }
                                catch(IOException e){

                                }
                            }
                        });

                }
                catch(IOException e){

                }



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

        if (view == buttonNope || view == buttonSave){
            currentDisplay = currentDisplay +1;
        }


        if(view == buttonFilter){
            Intent filterIntent = new Intent(this, FilterActivity.class);
            startActivity(filterIntent);
        } else if(view == buttonSave){
            final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String findEmail = user.getEmail();


       /*     myRef2.orderByChild("email").equalTo(findEmail).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    //User foundUser = dataSnapshot.getValue(User.class);
                    //ArrayList<Meal> findSaveMeals = foundUser.savedmeals;
                    String editKey = dataSnapshot.getKey();

                    //Meal currentMeal = dataSnapshot.getValue(Meal.class);
                        *//*
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

                         *//*



                    //findSaveMeals.add(currentMeal);

                    myRef2.child(editKey).child("savedmeals").push().setValue(currentSavedMeal);

                        *//* add to likedfood
                        new LikedFoodActivity().textViewFoodItem1.setText(thisMeal.name);
                        new LikedFoodActivity().textViewRest1.setText(thisMeal.restaurant);
                        new LikedFoodActivity().textViewPrice1.setText(thisMeal.price);
                         *//*



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
            });*/



            myRef.orderByKey().limitToFirst(currentDisplay).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {


                    Meal currentMeal = snapshot.getValue(Meal.class);
                    currentSavedMeal = currentMeal;


                    String findName = currentSavedMeal.name;
                    String findRestaurant = currentSavedMeal.restaurant;
                    String findPrice = currentSavedMeal.price;
                    String picReference = currentSavedMeal.picReference;
                    Boolean findVegan = currentSavedMeal.vegan;
                    Boolean findVegetarian = currentSavedMeal.vegetarian;
                    Boolean findGF = currentSavedMeal.glutenFree;
                    Boolean findDF = currentSavedMeal.dairyFree;
                    Boolean findNF = currentSavedMeal.nutFree;


                    StorageReference picRef = mStorageRef.child(picReference);
                    final File localFile;
                    try {
                        localFile = File.createTempFile("image", "jpg");

                        picRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(localFile));
                                    imageViewFood.setImageBitmap(bitmap);
                                }
                                catch(IOException e){

                                }
                            }
                        });

                    }
                    catch(IOException e){

                    }


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

            myRef.orderByKey().limitToFirst(currentDisplay).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String s) {
                    //Toast.makeText(LandingActivity.this, "Child Called", Toast.LENGTH_SHORT).show();

                    Meal newMeal = snapshot.getValue(Meal.class);


                    String findName = newMeal.name;
                    String findRestaurant = newMeal.restaurant;
                    String findPrice = newMeal.price;
                    String picReference = newMeal.picReference;
                    Boolean findVegan = newMeal.vegan;
                    Boolean findVegetarian = newMeal.vegetarian;
                    Boolean findGF = newMeal.glutenFree;
                    Boolean findDF = newMeal.dairyFree;
                    Boolean findNF = newMeal.nutFree;


                    Toast.makeText(LandingActivity.this, picReference, Toast.LENGTH_SHORT).show();

                    StorageReference picRef = mStorageRef.child(picReference);
                    final File localFile;
                    try {
                        localFile = File.createTempFile("image", "jpg");

                        picRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                                try {
                                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.fromFile(localFile));
                                    imageViewFood.setImageBitmap(bitmap);
                                }
                                catch(IOException e){

                                }
                            }
                        });

                    }
                    catch(IOException e){

                    }
                  //  Toast.makeText(LandingActivity.this, "Child Loaded", Toast.LENGTH_SHORT).show();


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
            Intent listViewIntent = new Intent(LandingActivity.this, ListActivity.class);
            startActivity(listViewIntent);

        }

    }


}