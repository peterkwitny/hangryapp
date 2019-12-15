package com.example.hangryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{
    TextView SettingTitle, AccountSettingsHeader, LegalHeader;
    Button NotificationPageRedirect, changePasswrodRedirect, buttonLogOut, buttonB2H;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SettingTitle = findViewById(R.id.SettingTitle);
        AccountSettingsHeader = findViewById(R.id.AccountSettingsHeader);
        LegalHeader = findViewById(R.id.LegalHeader);
        NotificationPageRedirect = findViewById(R.id.NotificationPageRedirect);
        changePasswrodRedirect = findViewById(R.id.changePasswrodRedirect);
        buttonLogOut = findViewById(R.id.buttonLogOut);
        buttonB2H = findViewById(R.id.buttonB2H);

        buttonLogOut.setOnClickListener(this);
        NotificationPageRedirect.setOnClickListener(this);
        changePasswrodRedirect.setOnClickListener(this);
        buttonB2H.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if(view == buttonLogOut){
            mAuth.signOut();;
            Intent landingIntent = new Intent(this, LoginActivity.class);
            startActivity(landingIntent);
        }else if (view == NotificationPageRedirect){
            Intent landingIntent = new Intent(this, NotificationsActivity.class);
            startActivity(landingIntent);
        }else if(view == changePasswrodRedirect) {
            Toast.makeText(this, "Functionality In Progress, Come back in 1 year", Toast.LENGTH_SHORT).show();
        }
        else if (view == buttonB2H){
            Intent landingIntent = new Intent(this, LandingActivity.class);
            startActivity(landingIntent);
        }
    }
}
