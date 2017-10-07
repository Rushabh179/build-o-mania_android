package com.project.rushabh.buildomania;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void buyButtonClicked(View view) {
        startActivity(new Intent(this,BuyActivity.class));
    }

    public void rentButtonClicked(View view) {
    }

    public void sellButtonClicked(View view) {
        startActivity(new Intent(this,SellActivity.class));
    }
}