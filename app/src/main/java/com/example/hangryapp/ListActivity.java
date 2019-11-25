package com.example.hangryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ListActivity extends AppCompatActivity implements View.OnClickListener {


    Button buttonSwipeView;
    ImageButton imageButton1, imageButton2, imageButton3, imageButton4;
    TextView textViewFoodItem1, textViewFoodItem2,textViewFoodItem3, textViewFoodItem4;
    TextView textViewRest1, textViewRest2, textViewRest3, textViewRest4;
    TextView textViewPrice1, textViewPrice2, textViewPrice3, textViewPrice4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        buttonSwipeView = findViewById(R.id.buttonSwipeView);

        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);

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

