package com.project.rushabh.buildomania;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] buyList;
        try {
            buyList = new BuyRentList().execute("sell").get().split("  ");
            ListView buyListView = (ListView) findViewById(R.id.buyListView);
            ListAdapter buyAdapter = new ListCustomAdapter(this, buyList);
            buyListView.setAdapter(buyAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
