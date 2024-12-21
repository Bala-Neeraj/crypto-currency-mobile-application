package com.example.sample;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class home2 extends AppCompatActivity {
    private long backPressedTime;
    private Toast backToast;
    public TextView favbtn;

    private RecyclerView recyclerView;


    // creating variable for recycler view,
    // adapter, array list, progress bar
    private RecyclerView currencyRV;
    private List<CurrencyModel> cryptolist=null;
    private ArrayList<CurrencyModel> currencyModalArrayList;
    private CurrencyRvAdapter currencyRVAdapter;
    private ProgressBar loadingPB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home2);
        favbtn = findViewById(R.id.favbtn);


        // initializing all our variables and array list.
        loadingPB = findViewById(R.id.idPBLoading);
        currencyRV = findViewById(R.id.idRVcurrency);
        currencyModalArrayList = new ArrayList<>();
        //app bar color setting
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));

        // initializing our adapter class.
        currencyRVAdapter = new CurrencyRvAdapter(currencyModalArrayList, this);

        // setting layout manager to recycler view.
        currencyRV.setLayoutManager(new LinearLayoutManager(this));

        // setting adapter to recycler view.
        currencyRV.setAdapter(currencyRVAdapter);


        // calling get data method to get data from API.
        getData();


        // on below line we are adding text watcher for our
        // edit text to check the data entered in edittext.




        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.home:

                    break;

                case R.id.search:

                    Intent intent2=new Intent(home2.this,search.class);
                    startActivity(intent2);
                    break;



                case R.id.fav:
                    Intent intent3=new Intent(home2.this,fav.class);
                    startActivity(intent3);
                    break;
                case R.id.dashboard:
                    Intent intent4=new Intent(home2.this,profile.class);
                    startActivity(intent4);
                    break;

            }


            return true;

        });
    }


    @Override
    public void onBackPressed() {

        if(backPressedTime+2000> System.currentTimeMillis()){
            backToast.cancel();

            super.onBackPressed();
            return;
        }else{
            backToast=
            Toast.makeText(getBaseContext(),"Press back to exit",Toast.LENGTH_SHORT);
            backToast.show();
        }

        backPressedTime = System.currentTimeMillis();

    }

    private void filter(String filter) {
        // on below line we are creating a new array list
        // for storing our filtered data.
        ArrayList<CurrencyModel> filteredlist = new ArrayList<>();
        // running a for loop to search the data from our array list.
        for (CurrencyModel item : currencyModalArrayList) {
            // on below line we are getting the item which are
            // filtered and adding it to filtered list.
            if (item.getName().toLowerCase().contains(filter.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        // on below line we are checking
        // weather the list is empty or not.
        if (filteredlist.isEmpty()) {
            // if list is empty we are displaying a toast message.
            Toast.makeText(this, "No currency found..", Toast.LENGTH_SHORT).show();
        } else {
            // on below line we are calling a filter
            // list method to filter our list.
            currencyRVAdapter.filterList(filteredlist);
        }
    }


    private void getData() {
        // creating a variable for storing our string.
        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        // creating a variable for request queue.
        RequestQueue queue = Volley.newRequestQueue(this);
        // making a json object request to fetch data from API.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            // inside on response method extracting data
            // from response and passing it to array list
            // on below line we are making our progress
            // bar visibility to gone.
            loadingPB.setVisibility(View.GONE);
            try {
                // extracting data from json.
                JSONArray dataArray = response.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject dataObj = dataArray.getJSONObject(i);
                    String symbol = dataObj.getString("symbol");
                    String name = dataObj.getString("name");
                    JSONObject quote = dataObj.getJSONObject("quote");
                    JSONObject USD = quote.getJSONObject("USD");
                    double price = USD.getDouble("price");
                    // adding all data to our array list.
                    currencyModalArrayList.add(new CurrencyModel(name, symbol, price));
                }
                // notifying adapter on data change.
                currencyRVAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                // handling json exception.
                e.printStackTrace();
                Toast.makeText(home2.this, "Something went amiss. Please try again later", Toast.LENGTH_SHORT).show();
            }
        }, error -> {
            // displaying error response when received any error.
            Toast.makeText(home2.this, "Something went amiss. Please try again later", Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                // in this method passing headers as
                // key along with value as API keys.
                HashMap<String, String> headers = new HashMap<>();
                headers.put("X-CMC_PRO_API_KEY", "9f99f5b8-dc78-4ca9-812b-f03573d6631a");
                // at last returning headers
                return headers;
            }
        };
        // calling a method to add our
        // json object request to our queue.
        queue.add(jsonObjectRequest);
    }
}
