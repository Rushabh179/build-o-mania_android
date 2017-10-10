package com.project.rushabh.buildomania;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BuyActivity extends AppCompatActivity {

    ListView buyListView;
    ListAdapter buyAdapter;

    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] buyList;
        try {
            buyList = new BuyRentList().execute("sell").get().split("  ");
            buyListView = (ListView) findViewById(R.id.buyListView);
            buyAdapter = new ListCustomAdapter(this, buyList);
            buyListView.setAdapter(buyAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        buyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(BuyActivity.this,title,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyActivity.this,HouseDetails.class).putExtra("title",title));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
