package com.example.hangryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

public class AddFoodActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner spinnerMealtimes, spinnerCuisine;
    EditText editTextMenuItem, editTextRestaurantName, editTextPrice;
    String mealTime, cuisine;
    Switch switchVegan, switchVegetarian, switchNutFree, switchGlutenFree, switchDairyFree;
//t
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

        switchVegan = (Switch) findViewById(R.id.switchVegan);
        switchVegetarian = findViewById(R.id.switchVegetarian);
        switchNutFree = findViewById(R.id.switchNutFree);
        switchDairyFree = findViewById(R.id.switchDairyFree);
        switchGlutenFree = findViewById(R.id.switchGlutenFree);


        switchVegan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true){
                    Toast.makeText(AddFoodActivity.this, "Vegan Checked", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddFoodActivity.this, "Vegan not Checked", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchVegetarian.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked == true){
                    Toast.makeText(AddFoodActivity.this, "Vegan Checked", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(AddFoodActivity.this, "Vegan not Checked", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

    @Override
    public void onItemSelected(AdapterView<?> spinnerClicked, View view, int position, long l) {

        if(spinnerClicked == spinnerMealtimes) {
            mealTime = spinnerClicked.getItemAtPosition(position).toString();

        } else if(spinnerClicked == spinnerCuisine) {

            cuisine = spinnerClicked.getItemAtPosition(position).toString();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {

    }
}
