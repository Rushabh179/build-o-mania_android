package com.project.rushabh.buildomania;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SellActivity extends AppCompatActivity {

    SharedPreferences sharedPref;

    ListView sellListView;
    ListAdapter sellAdapter;

    String title;
    String[] sellList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPref = getSharedPreferences(FixedVars.PREF_NAME, Context.MODE_PRIVATE);
        String username = sharedPref.getString(FixedVars.PREF_USER_NAME, "");

        try {
            sellList = new SellList().execute(username).get().split("  ");
            if (!sellList[0].isEmpty()) {
                sellListView = (ListView) findViewById(R.id.sellListView);
                sellAdapter = new ListCustomAdapter(this, sellList);
                sellListView.setAdapter(sellAdapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!sellList[0].isEmpty()) {
            sellListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    title = String.valueOf(parent.getItemAtPosition(position));
                    Toast.makeText(SellActivity.this, title, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SellActivity.this, HouseDetails.class).putExtra("title", title));
                }
            });
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SellActivity.this, SellInfo.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
