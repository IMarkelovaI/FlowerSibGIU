package com.example.flower;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserActivity extends AppCompatActivity {

    Button Kalend, Heal, Flow, Light, Akkn;
    RecyclerView flowList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        flowList = findViewById(R.id.moi_flow);
        Kalend = findViewById(R.id.button_kal);
        Heal = findViewById(R.id.button_heal);
        Flow = findViewById(R.id.button_flow);
        Light = findViewById(R.id.button_light);
        Akkn = findViewById(R.id.button_akk);

        Light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserActivity.this, LightActivity.class));

            }
        });
    }

}