package com.example.hangryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
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
import java.util.List;


public class LandingActivity extends AppCompatActivity implements View.OnClickListener{
    TextView textViewFoodName,textViewRestaurant,textViewPrice, textViewRestriction, textViewRestriction2, textViewRestriction3, textViewRestriction4, textViewRestriction5;
    Button buttonNope, buttonSave, buttonFilter, buttonListView;
    ImageView imageViewFood;
    int currentDisplay = 1;
    long totalNumber = 1;
    StorageReference mStorageRef;
    ArrayList<Meal> meals = new ArrayList<>();
    User currentUser;
    int index = 0;

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

        readData(new MyCallBack() {
            @Override
            public void onCallBackMeal(ArrayList<Meal> value) {
                meals = value;
                displayMeals(meals, index);
            }

            @Override
            public void onCallBackUser(User u) {
                currentUser = u;
            }
        });



    }

    void readData(final MyCallBack callBack){
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String email = user.getEmail();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef3 = database.getReference("Meal");
        final DatabaseReference myRef2 = database.getReference("User");

        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User u = snapshot.getValue(User.class);
                    if(u.email.equals(email)) {
                        callBack.onCallBackUser(u);
                        return;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Meal> meals = new ArrayList<>();
                for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                    Meal m = snapshot.getValue(Meal.class);
                    meals.add(m);
                }
                callBack.onCallBackMeal(meals);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    void displayMeals(ArrayList<Meal> meals, int index){
        if(index >= meals.size())
            return;

        Meal currentSavedMeal = meals.get(index);


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
        else{
            textViewRestriction.setText("");
        }
        if (findVegetarian == true) {
            textViewRestriction2.setText("Vegetarian");
        }
        else{
            textViewRestriction.setText("");
        }
        if (findGF == true) {
            textViewRestriction3.setText("Gluten Free");
        }
        else{
            textViewRestriction.setText("");
        }
        if (findDF == true) {
            textViewRestriction4.setText("Dairy Free");
        }
        else{
            textViewRestriction.setText("");
        }
        if (findNF == true) {
            textViewRestriction5.setText("Nut Free");
        }
        else{
            textViewRestriction.setText("");
        }

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

        if (view == buttonNope || view == buttonSave){
            currentDisplay = currentDisplay +1;
        }


        if(view == buttonFilter){
            Intent filterIntent = new Intent(this, FilterActivity.class);
            startActivity(filterIntent);
        } else if(view == buttonSave){
            if(index >= meals.size())
                return;
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            final DatabaseReference myRef2 = database.getReference("User");

            myRef2.orderByChild("email").equalTo(currentUser.email).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    String editKey = dataSnapshot.getKey();
                    myRef2.child(editKey).child("savedmeals").push().setValue(LandingActivity.this.meals.get(LandingActivity.this.index));
                    index++;
                    while(index < meals.size() && !assessPreference(meals.get(index), currentUser)) {
                        index++;
                    }
                    if(index < meals.size())
                        displayMeals(meals, index);
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
            if(index >= meals.size())
                return;

            index++;
            while(index < meals.size() && !assessPreference(meals.get(index), currentUser)) {
                index++;
            }
            if(index < meals.size())
                displayMeals(meals, index);

        }else if(view == buttonListView){
            Intent listViewIntent = new Intent(LandingActivity.this, ListActivity.class);
            startActivity(listViewIntent);

        }

    }

    boolean assessPreference(Meal meal, User u){
        Log.e("LandingActivity", u.vegan +"" + u.vegetarian + u.nutfree + u.glutenfree + u.dairyfree);
        Log.e("LandingActivity", meal.vegan +"" + meal.vegetarian + meal.nutFree + meal.glutenFree + meal.dairyFree);
        if((u.vegan == true && meal.vegan == false) || (u.glutenfree == true && meal.glutenFree == false) || (u.dairyfree == true && meal.dairyFree == false) || (u.vegetarian == true
        && meal.vegetarian == false) || (u.nutfree == true && meal.nutFree == false))
            return false;
        return true;
    }


}