package com.example.hangryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LikedFoodActivity extends AppCompatActivity {


    ImageView imageView1, imageView2, imageView3;
    TextView textViewFoodItem1, textViewFoodItem2,textViewFoodItem3;
    TextView textViewRest1, textViewRest2, textViewRest3;
    TextView textViewPrice1, textViewPrice2, textViewPrice3;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference myRef = database.getReference("User");

    private Context mContext;
    private StorageReference mStorageRef;

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
    protected void onCreate(final Bundle savedInstanceState) {
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

        this.mContext = mContext;

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        final String findEmail = user.getEmail();


        myRef.orderByChild("email").equalTo(findEmail).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                final String foundKey = dataSnapshot.getKey();

                myRef.child(foundKey).child("savedmeals").orderByKey().limitToLast(3).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<Meal> savedMeals = new ArrayList<>();


                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            savedMeals.add(snapshot.getValue(Meal.class));


                            //Uri myUri = Uri.parse(findMealImageRef);
                            //Bitmap foodImage = null;
                            //try {

                            //foodImage = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), myUri);
                            //} //catch (IOException e) {

                            //}
                        }



                        textViewFoodItem1.setText(savedMeals.get(0).name);
                        textViewFoodItem2.setText(savedMeals.get(1).name);
                        textViewFoodItem3.setText(savedMeals.get(2).name);

                        textViewRest1.setText(savedMeals.get(0).restaurant);
                        textViewRest2.setText(savedMeals.get(1).restaurant);
                        textViewRest3.setText(savedMeals.get(2).restaurant);

                        textViewPrice1.setText(savedMeals.get(0).price);
                        textViewPrice2.setText(savedMeals.get(1).price);
                        textViewPrice3.setText(savedMeals.get(2).price);

                        //imageView1.setImageBitmap(foodImage);

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


