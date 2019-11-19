package com.example.hangryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewSignUp, textViewEmailRegister, textViewRetypeEmailRegister,
            textViewPasswordRegister, textViewRetypePasswordRegister,textViewReadTerms;
    EditText editTextEmailRegister, editTextRetypeEmailRegister, editTextPasswordRegister,
            editTextRetypePasswordRegister;
    Button buttonRegisterUser;
    CheckBox checkBoxReadTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textViewSignUp = findViewById(R.id.textViewSignUp);
        textViewEmailRegister = findViewById(R.id.textViewEmailRegister);
        textViewRetypeEmailRegister = findViewById(R.id.textViewRetypeEmailRegister);
        textViewPasswordRegister = findViewById(R.id.textViewPasswordRegister);
        textViewRetypePasswordRegister = findViewById(R.id.textViewRetypePasswordRegister);
        textViewReadTerms = findViewById(R.id.textViewReadTerms);

        editTextEmailRegister = findViewById(R.id.editTextEmailRegister);
        editTextRetypeEmailRegister = findViewById(R.id.editTextRetypeEmailRegister);
        editTextPasswordRegister = findViewById(R.id.editTextPasswordRegister);
        editTextRetypePasswordRegister = findViewById(R.id.editTextRetypePasswordRegister);

        buttonRegisterUser = findViewById(R.id.buttonRegisterUser);

        checkBoxReadTerms = findViewById(R.id.checkBoxReadTerms);
    }

    @Override
    public void onClick(View v) {


    }
}
