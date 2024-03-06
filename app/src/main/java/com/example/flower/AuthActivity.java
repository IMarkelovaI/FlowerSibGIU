package com.example.flower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flower.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class AuthActivity extends AppCompatActivity {

    EditText userEmail, userPass;
    TextView linkToReg;
    Button button;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        userEmail = findViewById(R.id.email_user_auth);
        userPass = findViewById(R.id.login_pass_auth);
        button = findViewById(R.id.button_auth);
        linkToReg = findViewById(R.id.link_to_reg);
        auth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = userPass.getText().toString().trim();
                String email = userEmail.getText().toString().trim();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Toast.makeText(AuthActivity.this, "Авторизация успешна", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(AuthActivity.this, UserActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(AuthActivity.this, "Авторизация провалена", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    } else {
                        userPass.setError("Пустые поля не допускаются");
                    }
                } else if (email.isEmpty()) {
                    userEmail.setError("Пустые поля не допускаются");
                } else {
                    userEmail.setError("Пожалуйста, введите правильный адрес электронной почты");
                }
            }
        });

        linkToReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AuthActivity.this, MainActivity.class));
            }
        });
    }

}