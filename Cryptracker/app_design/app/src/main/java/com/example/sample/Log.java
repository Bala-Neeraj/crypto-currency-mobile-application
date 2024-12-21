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


public class Log extends AppCompatActivity {
    private FirebaseAuth mAuth;
    // ...
// Initialize Firebase Auth
    public EditText Email1;
    public EditText Password1;
    public EditText donthaveacc;
    public ProgressBar progressBar;
    public  Button LoginBtn;
    // Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        progressBar = findViewById(R.id.progressBar);

        donthaveacc=findViewById(R.id.donthaveacc);
        Email1 = findViewById(R.id.Email);
        Password1 = findViewById(R.id.Password);

        LoginBtn = findViewById(R.id.LoginBtn);

        donthaveacc.setOnClickListener(view -> {
            Intent intent
                    = new Intent(Log.this,
                    Reg.class);
            startActivity(intent);
        });
        LoginBtn.setOnClickListener(v -> loginNewUser());
    }

    private void loginNewUser()
    {

        // show the visibility of progress bar to show loading
        progressBar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password;
        email = Email1.getText().toString().trim();
        password = Password1.getText().toString().trim();

        // Validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        else if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }else
        {

            // create new user or register new user
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(task -> {


                if (task.isSuccessful()) {
                    Toast.makeText(Log.this,"User Logged in Successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Log.this,home2.class));


                    // hide the progress bar

                    // if the user created intent to login activity

                } else {

                    Toast.makeText(Log.this,"User LogIn failed",Toast.LENGTH_SHORT).show();
                }

            });


        }}




}
