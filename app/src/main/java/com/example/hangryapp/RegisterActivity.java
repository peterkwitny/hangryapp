package com.example.hangryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textViewSignUp, textViewEmailRegister, textViewRetypeEmailRegister,
            textViewPasswordRegister, textViewRetypePasswordRegister,textViewReadTerms;
    EditText editTextEmailRegister, editTextRetypeEmailRegister, editTextPasswordRegister,
            editTextRetypePasswordRegister;
    Button buttonRegisterUser;
    CheckBox checkBoxReadTerms;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textViewSignUp = findViewById(R.id.textViewSignUp);
        textViewEmailRegister = findViewById(R.id.textViewEmailRegister);
        textViewRetypeEmailRegister = findViewById(R.id.textViewRetypeEmailRegister);
        textViewPasswordRegister = findViewById(R.id.textViewPasswordRegister);
        textViewRetypePasswordRegister = findViewById(R.id.textViewRetypePasswordRegister);

        editTextEmailRegister = findViewById(R.id.editTextEmailRegister);
        editTextRetypeEmailRegister = findViewById(R.id.editTextRetypeEmailRegister);
        editTextPasswordRegister = findViewById(R.id.editTextPasswordRegister);
        editTextRetypePasswordRegister = findViewById(R.id.editTextRetypePasswordRegister);



        buttonRegisterUser = findViewById(R.id.buttonRegisterUser);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        String emailRegister = editTextEmailRegister.getText().toString();
        String retypeEmailRegister = editTextRetypeEmailRegister.getText().toString();
        String passwordRegister = editTextPasswordRegister.getText().toString();
        String retypePasswordRegister = editTextRetypePasswordRegister.getText().toString();

        if (emailRegister.equalsIgnoreCase(retypeEmailRegister) && passwordRegister.equals(retypePasswordRegister)){
            //allow log in
            mAuth.createUserWithEmailAndPassword(emailRegister, passwordRegister)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()) {
                                //Display success message
                                Toast.makeText(RegisterActivity.this, "User Registration Successful", Toast.LENGTH_SHORT).show();



                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(RegisterActivity.this, "User Registration Failed", Toast.LENGTH_SHORT).show();


                            }

                        }
                    });


        } else {
            //can't log in
            editTextRetypeEmailRegister.setError("Values need to match");
            editTextRetypePasswordRegister.setError("Values need to match");

        }




    }



    }

