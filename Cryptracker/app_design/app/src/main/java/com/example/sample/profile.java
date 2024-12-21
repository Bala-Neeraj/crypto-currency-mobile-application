package com.example.sample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Objects;

public class profile extends AppCompatActivity {
    private Button logout1;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));


        ///app bar color setting

        user=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("users");
        userID=user.getUid();
        final TextView nameTextview=(TextView) findViewById(R.id.profileName);
        final TextView emailTextview=(TextView) findViewById(R.id.profileemail) ;
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                users userprofile=snapshot.getValue(users.class);
                if(userprofile!=null){
                    String username=userprofile.name;
                    String useremail=userprofile.email;

                    nameTextview.setText(username);
                    emailTextview.setText(useremail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile.this,"something gone wrong",Toast.LENGTH_LONG).show();

            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.home:

                    Intent intent2=new Intent(profile.this,home2.class);
                    startActivity(intent2);

                    break;
                case R.id.fav:

                    Intent intent3=new Intent(profile.this,fav.class);
                    startActivity(intent3);
                    break;
                case R.id.dashboard:

                    break;

            }


            return true;

        });
    }
    public void logout(View v){
    FirebaseAuth.getInstance().signOut();
    startActivity(new Intent(profile.this,Reg.class));

    }
}