package com.example.sample;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Reg extends AppCompatActivity {
    private FirebaseAuth mAuth;
    // ...
// Initialize Firebase Auth
    EditText Username1;
     EditText Email1;
     EditText Password1;
     EditText ConfirmPassword1;
     Button Register1;
     EditText haveacc1;
     DatabaseReference usersdb;
     ProgressBar progressBar;
    // Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        haveacc1=findViewById(R.id.haveacc);

        progressBar = findViewById(R.id.progressBar);
usersdb= FirebaseDatabase.getInstance().getReference().child("users");

       Username1 = findViewById(R.id.Username);
        Email1 = findViewById(R.id.Email);
        Password1 = findViewById(R.id.Password);
        ConfirmPassword1 = findViewById(R.id.ConfirmPassword);
        Register1 = findViewById(R.id.RegisterBtn);
        haveacc1.setOnClickListener(view -> {
            Intent intent
                    = new Intent(Reg.this,
                    Log.class);
            startActivity(intent);
        });


        Register1.setOnClickListener(v -> registerNewUser());
    }

    private void registerNewUser()
    {

        // show the visibility of progress bar to show loading
        progressBar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password,name;
        name=Username1.getText().toString();
        email = Email1.getText().toString();
        password = Password1.getText().toString();
        users users=new users(
                name,email
        );
                usersdb.push().setValue(users);

        // Validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
            return;
        }

        // create new user or register new user
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(),
                        "Registration successful!",
                        Toast.LENGTH_LONG)
                        .show();

                // hide the progress bar
                progressBar.setVisibility(View.GONE);

                // if the user created intent to login activity
                Intent intent
                        = new Intent(Reg.this,
                        Log.class);
                startActivity(intent);
            }
            else {

                // Registration failed
                Toast.makeText(
                        getApplicationContext(),
                        "Registration failed!!"
                                + " Please try again later",
                        Toast.LENGTH_LONG)
                        .show();

                // hide the progress bar
                progressBar.setVisibility(View.GONE);
            }

        });


    }



}
