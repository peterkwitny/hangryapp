package com.example.hangryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LikedFoodActivity extends AppCompatActivity {

    ImageView imageView1, imageView2, imageView3, imageView4;
    TextView textViewFoodItem1, textViewFoodItem2,textViewFoodItem3, textViewFoodItem4;
    TextView textViewRest1, textViewRest2, textViewRest3, textViewRest4;
    TextView textViewPrice1, textViewPrice2, textViewPrice3, textViewPrice4;

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
        imageView4 = findViewById(R.id.imageView4);

        textViewFoodItem1 = findViewById(R.id.textViewFoodItem1);
        textViewFoodItem2 = findViewById(R.id.textViewFoodItem2);
        textViewFoodItem3 = findViewById(R.id.textViewFoodItem3);
        textViewFoodItem4 = findViewById(R.id.textViewFoodItem4);

        textViewRest1 = findViewById(R.id.textViewRest1);
        textViewRest2 = findViewById(R.id.textViewRest2);
        textViewRest3 = findViewById(R.id.textViewRest3);
        textViewRest4 = findViewById(R.id.textViewRest4);

        textViewPrice1 = findViewById(R.id.textViewPrice1);
        textViewPrice2 = findViewById(R.id.textViewPrice2);
        textViewPrice3 = findViewById(R.id.textViewPrice3);
        textViewPrice4 = findViewById(R.id.textViewPrice4);




    }
}
