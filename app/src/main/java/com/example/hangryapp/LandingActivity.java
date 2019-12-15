package com.example.hangryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class LandingActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textViewFoodName,textViewRestaurant,textViewPrice, textViewRestriction, textViewRestriction2, textViewRestriction3, textViewRestriction4, textViewRestriction5;
    Button buttonNope, buttonSave, buttonFilter, buttonListView;
    ImageView imageViewFood;
    int currentDisplay = 1;
    long totalNumber = 1;
    Meal currentSavedMeal, newMeal;
    StorageReference mStorageRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* final StorageReference mStorageRef; */
        mStorageRef = FirebaseStorage.getInstance().getReference();

        setContentView(R.layout.activity_landing);


        textViewFoodName = findViewById(R.id.textViewFoodName);
        textViewRestaurant = findViewById(R.id.textViewSavedRestaurant);
        textViewPrice = findViewById(R.id.textViewSavedPrice);
        textViewRestriction = findViewById(R.id.textViewRestriction);
        textViewRestriction2 = findViewById(R.id.textViewRestriction2);
        textViewRestriction3 = findViewById(R.id.textViewRestriction3);
        textViewRestriction4 = findViewById(R.id.textViewRestriction4);
        textViewRestriction5 = findViewById(R.id.textViewRestriction5);
        buttonNope = findViewById(R.id.buttonNope);
        buttonSave = findViewById(R.id.buttonSave);
        buttonFilter = findViewById(R.id.buttonFilter);
        buttonListView = findViewById(R.id.buttonListView);
        imageViewFood = findViewById(R.id.imageViewSavedFood);

        buttonListView.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        buttonFilter.setOnClickListener(this);
        buttonNope.setOnClickListener(this);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Meal");


       myRef.orderByKey().limitToFirst(currentDisplay).addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             //  Toast.makeText(LandingActivity.this, String.valueOf(dataSnapshot.getChildrenCount()), Toast.LENGTH_SHORT).show();
               Meal foundMeal = new Meal();
               for(DataSnapshot snap: dataSnapshot.getChildren()){
                   foundMeal = snap.getValue(Meal.class);
                   currentSavedMeal = foundMeal;
               }

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

        }else if (item.getItemId() == R.id.itemLanding) {
            Toast.makeText(this, "You are already in the main page", Toast.LENGTH_SHORT).show();
        }
       else if(item.getItemId() == R.id.itemFAQ){
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
        final DatabaseReference myRef3 = database.getReference("Meal");
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

            myRef2.orderByChild("email").equalTo(findEmail).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String editKey = dataSnapshot.getKey();
                    myRef2.child(editKey).child("savedmeals").push().setValue(currentSavedMeal);
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

            Toast.makeText(this, "Added to Liked Foods!", Toast.LENGTH_SHORT).show();

            myRef3.orderByKey().limitToFirst(currentDisplay).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for(DataSnapshot snap: dataSnapshot.getChildren()){
                        currentSavedMeal = snap.getValue(Meal.class);
                    }
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
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });



        } else if(view == buttonNope){

            myRef3.orderByKey().limitToFirst(currentDisplay).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                  //  Meal newMeal = new Meal();
                    for(DataSnapshot snap: dataSnapshot.getChildren()){
                        newMeal = snap.getValue(Meal.class);
                    }

                    String findName = newMeal.name;
                    String findRestaurant = newMeal.restaurant;
                    String findPrice = newMeal.price;
                    String picReference = newMeal.picReference;
                    Boolean findVegan = newMeal.vegan;
                    Boolean findVegetarian = newMeal.vegetarian;
                    Boolean findGF = newMeal.glutenFree;
                    Boolean findDF = newMeal.dairyFree;
                    Boolean findNF = newMeal.nutFree;

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
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }else if(view == buttonListView){
            Intent listViewIntent = new Intent(LandingActivity.this, ListActivity.class);
            startActivity(listViewIntent);

        }

    }


}