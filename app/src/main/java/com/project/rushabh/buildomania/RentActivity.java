package com.project.rushabh.buildomania;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;

public class RentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] rentList = {"3pac", "is the", "greatest"};
        ListView rentListView = (ListView) findViewById(R.id.rentListView);
        ListAdapter rentAdapter = new ListCustomAdapter(this, rentList);
        rentListView.setAdapter(rentAdapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
