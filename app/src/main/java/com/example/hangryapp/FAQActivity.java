package com.example.hangryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class FAQActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonGoBackToLanding;
    TextView textViewFaq, textViewFaqOne, textViewFaqTwo, textViewFaqThree;
    EditText editTextFaqAnswerOne, editTextFaqAnswerTwo, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        buttonGoBackToLanding = findViewById(R.id.buttonGoBackToLanding);

        textViewFaq = findViewById(R.id.textViewFaq);
        textViewFaqOne = findViewById(R.id.textViewFaqOne);
        textViewFaqTwo = findViewById(R.id.textViewFaqTwo);
        textViewFaqThree = findViewById(R.id.textViewFaqThree);
        editTextFaqAnswerOne = findViewById(R.id.editTextFaqAnswerOne);
        editTextFaqAnswerTwo = findViewById(R.id.editTextFaqAnswerTwo);
        editText3 = findViewById(R.id.editText3);

        buttonGoBackToLanding.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        if(view == buttonGoBackToLanding){
            Intent filterIntent = new Intent(this, LandingActivity.class);
            startActivity(filterIntent);
        }
    }
}
