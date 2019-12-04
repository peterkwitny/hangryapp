package com.example.hangryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PreferenceActivity extends AppCompatActivity  implements View.OnClickListener{

    Switch switchVeganUser, switchGlutenFreeUser, switchVegetarianUser, switchDairyFreeUser, switchNutFreeUser;
    Button buttonAddPreferences;
    Boolean switchVeganUserChecked, switchGlutenFreeUserChecked, switchVegetarianUserChecked, switchDairyFreeUserChecked, switchNutFreeUserChecked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);

        switchVeganUser = findViewById(R.id.switchVeganUser);
        switchVegetarianUser = findViewById(R.id.switchVegetarianUser);
        switchNutFreeUser = findViewById(R.id.switchNutFreeUser);
        switchDairyFreeUser = findViewById(R.id.switchDairyFreeUser);
        switchGlutenFreeUser = findViewById(R.id.switchGlutenFreeUser);

        buttonAddPreferences = findViewById(R.id.buttonAddPreferences);

        buttonAddPreferences.setOnClickListener(this);


        switchVeganUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchVeganUserChecked = true;
                } else {
                    switchVeganUserChecked = false;


                }
            }
        });

        switchVegetarianUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchVegetarianUserChecked = true;
                } else {
                    switchVegetarianUserChecked = false;


                }
            }
        });


        switchNutFreeUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchNutFreeUserChecked = true;
                } else {
                    switchNutFreeUserChecked = false;


                }
            }
        });


        switchDairyFreeUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchDairyFreeUserChecked = true;
                } else {
                    switchDairyFreeUserChecked = false;

                }
            }
        });

        switchGlutenFreeUser.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchGlutenFreeUserChecked = true;
                } else {
                    switchGlutenFreeUserChecked = false;

                }
            }
        });


    }


    @Override
    public void onClick(View v) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("User");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (buttonAddPreferences == v) {

            String userEmail = user.getEmail();
            String password = "password";

            User myUser = new User(userEmail, password, switchVeganUserChecked, switchGlutenFreeUserChecked, switchVegetarianUserChecked, switchDairyFreeUserChecked, switchNutFreeUserChecked);
            myRef.push().setValue(myUser);

            Toast.makeText(this, "User Preferences Submitted", Toast.LENGTH_SHORT).show();
        }

    }
}
