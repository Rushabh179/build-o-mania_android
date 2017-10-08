package com.project.rushabh.buildomania;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.neel.articleshubapi.restapi.beans.UserDetail;
import com.neel.articleshubapi.restapi.request.HeaderTools;
import com.neel.articleshubapi.restapi.request.RequestTask;

import org.springframework.http.HttpMethod;

public class Home extends AppCompatActivity {

    String token = null, userName = null;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    //UserDetail ud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

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
                //Toast.makeText(this, ud.getUserName(), Toast.LENGTH_SHORT).show();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            /*RequestTask<String> logoutRequest = new RequestTask<String>(String.class, HttpMethod.DELETE,
                    new HeaderTools.EntryImp("token", token));
            logoutRequest.execute(FixedVars.BASE_URL + "/authentication/" + ud.getUserName());
            editor.clear();
            editor.apply();
            //listSelected.clear();
            Intent myIntent = new Intent(Home.this, LoginActivity.class);
            startActivity(myIntent);
            Toast.makeText(this, "You have successfully logged out!!!", Toast.LENGTH_SHORT).show();
            finish();*/
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}