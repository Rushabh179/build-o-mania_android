package com.project.rushabh.buildomania;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.neel.articleshubapi.restapi.beans.UserDetail;

public class Home extends AppCompatActivity {

    String token = null, userName = null;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPref = getSharedPreferences(FixedVars.PREF_NAME, Context.MODE_PRIVATE);
        userName = sharedPref.getString(FixedVars.PREF_USER_NAME, "");
        token = sharedPref.getString(FixedVars.PREF_LOGIN_TOKEN, "");

        //Checking for internet connection
        if (NetworkStatus.getInstance(this).isOnline()) {

            setContentView(R.layout.activity_home);

            if (userName.equals("") && !FixedVars.SENTFROMSTARTPAGE) {
                Intent myIntent = new Intent(Home.this, LoginActivity.class);
                startActivity(myIntent);
                finish();
            } else {
                Toast.makeText(this, userName, Toast.LENGTH_SHORT).show();
                CardView buyCardView, sellCardView, rentCardView;
                buyCardView = (CardView) findViewById(R.id.buyCardView);
                sellCardView = (CardView) findViewById(R.id.sellCardView);
                rentCardView = (CardView) findViewById(R.id.rentCardView);

                buyCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Home.this, BuyActivity.class));
                    }
                });

                sellCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Home.this, SellList.class));
                    }
                });

                rentCardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Home.this, LoginActivity.class));

                    }
                });
            }
        }
        else {
            NetworkStatus.getInstance(this).buildDialog(this).show();
        }
    }
}