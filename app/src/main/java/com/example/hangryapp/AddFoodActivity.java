package com.example.hangryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFoodActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner spinnerMealtimes, spinnerCuisine;
    EditText editTextMenuItem, editTextRestaurantName, editTextPrice;
    String mealtime, cuisine;
    Switch switchVegan, switchVegetarian, switchNutFree, switchGlutenFree, switchDairyFree;
    Boolean switchVeganChecked = false;
    Boolean switchVegetarianChecked = false;
    Boolean switchNutFreeChecked = false;
    Boolean switchGlutenFreeChecked = false;
    Boolean switchDairyFreeChecked = false;
    Button buttonAddMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        //Color Scheme Implementation
        ConstraintLayout constraintLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        //Spinner Mealtimes Implementation
        spinnerMealtimes = findViewById(R.id.spinnerMealtimes);
        ArrayAdapter<CharSequence> adapterMealtimes = ArrayAdapter.createFromResource(this, R.array.mealtimes, android.R.layout.simple_spinner_item);
        adapterMealtimes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMealtimes.setAdapter(adapterMealtimes);
        spinnerMealtimes.setOnItemSelectedListener(this);

        //Spinner Cuisine Implementation
        spinnerCuisine = findViewById(R.id.spinnerCuisine);
        ArrayAdapter<CharSequence> adapterCuisines = ArrayAdapter.createFromResource(this, R.array.cuisines, android.R.layout.simple_spinner_item);
        adapterCuisines.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCuisine.setAdapter(adapterCuisines);
        spinnerCuisine.setOnItemSelectedListener(this);


        editTextMenuItem = findViewById(R.id.editTextMenuItem);
        editTextRestaurantName = findViewById(R.id.editTextRestaurantName);
        editTextPrice = findViewById(R.id.editTextPrice);

        switchVegan = findViewById(R.id.switchVegan);
        switchVegetarian = findViewById(R.id.switchVegetarian);
        switchNutFree = findViewById(R.id.switchNutFree);
        switchDairyFree = findViewById(R.id.switchDairyFree);
        switchGlutenFree = findViewById(R.id.switchGlutenFree);

        buttonAddMeal = findViewById(R.id.buttonAddMeal);
        buttonAddMeal.setOnClickListener(this);

        switchVegan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchVeganChecked = true;
                } else {
                    switchVeganChecked = false;

                }
            }
        });

        switchVegetarian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchVegetarianChecked = true;
                } else {
                    switchVegetarianChecked = false;

                }
            }
        });


        switchNutFree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchNutFreeChecked = true;
                } else {
                    switchNutFreeChecked = false;

                }
            }
        });


        switchDairyFree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchDairyFreeChecked = true;
                } else {
                    switchDairyFreeChecked = false;

                }
            }
        });

        switchGlutenFree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchGlutenFreeChecked = true;
                } else {
                    switchGlutenFreeChecked = false;

                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> spinnerClicked, View view, int position, long l) {

        if (spinnerClicked == spinnerMealtimes) {
            mealtime = spinnerClicked.getItemAtPosition(position).toString();

        } else if (spinnerClicked == spinnerCuisine) {
            cuisine = spinnerClicked.getItemAtPosition(position).toString();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Meal");
        String name = editTextMenuItem.getText().toString();
        String restaurant = editTextRestaurantName.getText().toString();
        String price = editTextPrice.getText().toString();


        if (buttonAddMeal == v) {

            Meal myMeal = new Meal(name, restaurant, mealtime, cuisine, price, switchVeganChecked, switchGlutenFreeChecked, switchVegetarianChecked, switchDairyFreeChecked, switchNutFreeChecked, "a");
            myRef.push().setValue(myMeal);


            Intent mainIntent = new Intent(this, AddPhotoActivity.class);
            startActivity(mainIntent);

        }

    }
}
