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

        String[] buyList = {"potato","tomato","salad"};
        ListView buyListView = (ListView) findViewById(R.id.buyListView);
        ListAdapter buyAdapter = new ListCustomAdapter(this, buyList);
        buyListView.setAdapter(buyAdapter);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
