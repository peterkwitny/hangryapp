package com.example.hangryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {



    TextView textViewNotifications, textViewReminder, textViewPushNotificationOnOff,
            textViewFoodInspiration, textViewPushNotificationFoodInspo;

    Switch switchReminderPushNotification, switch2;

    Spinner spinner2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        textViewNotifications = findViewById(R.id.textViewNotifications);
        textViewReminder = findViewById(R.id.textViewReminder);
        textViewPushNotificationOnOff = findViewById(R.id.textViewPushNotificationOnOff);
        textViewFoodInspiration = findViewById(R.id.textViewPushNotificationOnOff);
        textViewPushNotificationFoodInspo = findViewById(R.id.textViewPushNotificationFoodInspo);

        switchReminderPushNotification = findViewById(R.id.switchReminderPushNotification);
        switch2 = findViewById(R.id.switch2);

        spinner2 = findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.notification_times, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter);
        spinner2.setOnItemSelectedListener(this);



    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
