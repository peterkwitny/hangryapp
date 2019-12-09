package com.example.hangryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LikedFoodActivity extends AppCompatActivity {

    private ArrayList<User> likedFood;

    ImageView imageView1, imageView2, imageView3;
    TextView textViewFoodItem1, textViewFoodItem2,textViewFoodItem3;
    TextView textViewRest1, textViewRest2, textViewRest3;
    TextView textViewPrice1, textViewPrice2, textViewPrice3;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("User");

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemSettings){

            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);

        } else if(item.getItemId() == R.id.itemLikedFood){

            Toast.makeText(this, "You are already in Liked Food page", Toast.LENGTH_SHORT).show();

        } else if(item.getItemId() == R.id.itemFAQ){

            Intent faqIntent = new Intent(this, FAQActivity.class);
            startActivity(faqIntent);

        }   else if (item.getItemId() == R.id.itemAddFood){
            Intent addFoodIntent = new Intent(this, AddFoodActivity.class);
            startActivity(addFoodIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_food);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);

        textViewFoodItem1 = findViewById(R.id.textViewFoodItem1);
        textViewFoodItem2 = findViewById(R.id.textViewFoodItem2);
        textViewFoodItem3 = findViewById(R.id.textViewFoodItem3);

        textViewRest1 = findViewById(R.id.textViewRest1);
        textViewRest2 = findViewById(R.id.textViewRest2);
        textViewRest3 = findViewById(R.id.textViewRest3);

        textViewPrice1 = findViewById(R.id.textViewPrice1);
        textViewPrice2 = findViewById(R.id.textViewPrice2);
        textViewPrice3 = findViewById(R.id.textViewPrice3);

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String findEmail = user.getEmail();


        myRef.orderByChild("email").equalTo(findEmail).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final User foundUser = dataSnapshot.getValue(User.class);
                final DatabaseReference savedMealsRef = database.getReference("savedmeals");


                savedMealsRef.orderByKey().limitToLast(3).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        String foundSavedMeals = dataSnapshot.getKey();
                        String foundMeal = foundSavedMeals.






                        //textViewFoodItem1.setText(foundMeal);



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
}


