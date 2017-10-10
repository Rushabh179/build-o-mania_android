package com.project.rushabh.buildomania;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import android.util.EventLog;
import android.widget.TextView;
import android.widget.Toast;

public class HouseDetails extends AppCompatActivity {

    //TODO: Java server
    /*private long houseDetailId;
    //private String author;
    private String title;
    private Description description;
    private String category;*/

    String title;
    String details[];

    private TextView titleTv;
    private TextView descriptionTv;
    private TextView addressTv;
    private TextView priceTv;
    private TextView categoryTv;
    private TextView usernameTv;
    private TextView emailTv;
    private TextView phoneTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = getIntent().getStringExtra("title");
        try {
            details = new GetDetails().execute(title).get().split("     ");
            //Toast.makeText(this,details[0],Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        titleTv = (TextView) findViewById(R.id.titleTv);
        descriptionTv = (TextView) findViewById(R.id.descriptionTv);
        addressTv = (TextView) findViewById(R.id.addressTv);
        priceTv = (TextView) findViewById(R.id.priceTv);
        categoryTv = (TextView) findViewById(R.id.categoryTv);
        usernameTv = (TextView) findViewById(R.id.usernameTv);
        emailTv = (TextView) findViewById(R.id.emailTv);
        phoneTv = (TextView) findViewById(R.id.phoneTv);


        titleTv.setText(title);
        descriptionTv.setText(details[0]);
        addressTv.setText(details[1]);
        priceTv.setText(details[2]);
        categoryTv.setText(String.format("For %s", details[3]));
        usernameTv.setText(details[4]);
        emailTv.setText(details[5]);
        phoneTv.setText(details[6]);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
