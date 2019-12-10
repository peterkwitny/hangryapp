package com.example.hangryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FilterActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{

    //declare variables DONE
    //initialize variables DONE
    //menu options
    //get switches to change user preferences on toggle DONE
    //add slidey function capability

    SeekBar seekBarPrice;
    Switch switchVeg, switchVegan, switchDF, switchNF, switchGF;
    Boolean switchVegTrue, switchVeganTrue,switchDFTrue, switchNFTrue, switchGFTrue;
    TextView textViewPriceMinimum, textViewPriceMaximum, getTextViewPriceMaximumValue, textView0, textView50, textView100, textViewTitleFilter;
    EditText editTextCusine1, editTextCusine2;
    Button buttonSetFilters;

    Double dbPricePercent = 0.20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        switchVegan = findViewById(R.id.switchVegan);
        switchVeg = findViewById(R.id.switchVeg);
        switchNF = findViewById(R.id.switchNF);
        switchDF = findViewById(R.id.switchDF);
        switchGF = findViewById(R.id.switchGF);

        textViewPriceMinimum = findViewById(R.id.textViewPriceMinimum);
        textViewPriceMaximum = findViewById(R.id.textViewPriceMaximum);
        getTextViewPriceMaximumValue = findViewById(R.id.textViewPriceMaximumValue);
        textView0 = findViewById(R.id.textView0);
        textView50 = findViewById(R.id.textView50);
        textView100 = findViewById(R.id.textView100);
        textViewTitleFilter = findViewById(R.id.textViewTitleFilter);

        seekBarPrice = findViewById(R.id.seekBarPrice);

        buttonSetFilters = findViewById(R.id.buttonSetFilters);
        buttonSetFilters.setOnClickListener(this);

        seekBarPrice.setOnSeekBarChangeListener(this);

        switchVegan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchVeganTrue = true;
                } else {
                    switchVeganTrue = false;
                }
            }
        });

        switchVeg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchVegTrue= true;
                } else {
                    switchVegTrue = false;
                }
            }
        });


        switchNF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchNFTrue = true;
                } else {
                    switchNFTrue = false;
                }
            }
        });


        switchDF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchDFTrue = true;
                } else {
                    switchDFTrue = false;
                }
            }
        });

        switchGF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override

            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    switchGFTrue = true;
                } else {
                    switchGFTrue = false;
                }
            }
        });


    }

    @Override
    public void onClick(View view) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("User");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String currentUserEmail = user.getEmail();

        if(view == buttonSetFilters){
            myRef.orderByChild("email").equalTo(currentUserEmail).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    User u = dataSnapshot.getValue(User.class);
                    String key = dataSnapshot.getKey();

                    u.dairyfree = switchDFTrue;
                    u.glutenfree = switchGFTrue;
                    u.nutfree = switchNFTrue;
                    u.vegan = switchVeganTrue;
                    u.vegetarian = switchDFTrue;
                    u.priceMax = dbPricePercent;

                    if(editTextCusine1.getText().toString() != ""){
                        u.cusinePreference1 = editTextCusine1.getText().toString();
                    }

                    if(editTextCusine2.getText().toString() != ""){
                        u.cusinePreference2 = editTextCusine2.getText().toString();
                    }

                    myRef.child(key).setValue(u);

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

            Toast.makeText(this, "User Filters Set", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        dbPricePercent = i*1.0;
        textViewPriceMaximum.setText(Double.toString(dbPricePercent));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
