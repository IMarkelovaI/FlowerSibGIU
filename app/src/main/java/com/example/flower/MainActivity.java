package com.example.flower;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.flower.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText userLog, userEmail, userPass;
    TextView linkToAuth;
    Button button;

    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLog = findViewById(R.id.login_user);
        userEmail = findViewById(R.id.login_email);
        userPass = findViewById(R.id.login_pass);
        button = findViewById(R.id.button_reg);
        linkToAuth = findViewById(R.id.link_to_auth);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = userLog.getText().toString().trim();
                String email = userEmail.getText().toString().trim();
                String pass = userPass.getText().toString().trim();

                //ошибки
                if (login.isEmpty()) {
                    userLog.setError("Электронная почта не может быть пустой");
                }
                if (pass.isEmpty()) {
                    userPass.setError("Пароль не может быть пустым");
                }
                if (email.isEmpty()) {
                    userEmail.setError("Логин не может быть пустым");
                }

                //регистрация
                else {
                    auth.createUserWithEmailAndPassword(email, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            User user = new User();

                            user.setEmail(email);
                            user.setLogin(login);
                            user.setPass(pass);

                            users.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            Toast.makeText(MainActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(MainActivity.this, AuthActivity.class));
                                            finish();

                                        }
                                    });

                        }
                    });
                }
            }
        });

        linkToAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AuthActivity.class));
            }
        });
    }
}