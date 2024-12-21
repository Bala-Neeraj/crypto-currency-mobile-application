package com.example.sample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class fav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        //app bar color setting

        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));


        // navigation sysytem
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.home:

                    Intent intent2=new Intent(fav.this,home2.class);
                    startActivity(intent2);

                    break;
                    case R.id.fav:
                    break;
                case R.id.search:
                    Intent intent4=new Intent(fav.this,search.class);
                    startActivity(intent4);
                    break;
                case R.id.dashboard:
                    Intent intent3=new Intent(fav.this,profile.class);
                    startActivity(intent3);
                    break;

            }


            return true;

        });

        //fav sysytem



    }
}