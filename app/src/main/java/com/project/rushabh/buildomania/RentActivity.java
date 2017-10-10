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

public class RentActivity extends AppCompatActivity {

    ListView rentListView;
    ListAdapter rentAdapter;

    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] rentList = new String[0];
        try {
            rentList = new BuyRentList().execute("rent").get().split("  ");
            rentListView = (ListView) findViewById(R.id.rentListView);
            rentAdapter = new ListCustomAdapter(this, rentList);
            rentListView.setAdapter(rentAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

        rentListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(RentActivity.this,title,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RentActivity.this,HouseDetails.class).putExtra("title",title));
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
