package com.example.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    Button Login,Register;
    private FirebaseAuth mAuth;
    private Button btnlayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();

        Register = findViewById(R.id.Register);
        Login = findViewById(R.id.Login);

        Register.setOnClickListener((View v) -> {
            startActivity(new Intent(MainActivity.this, Reg.class));
        });
        Login.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, com.example.sample.Log.class)));

    }}
