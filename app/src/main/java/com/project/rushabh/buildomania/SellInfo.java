package com.project.rushabh.buildomania;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SellInfo extends AppCompatActivity {

    SharedPreferences sharedPref;

    private EditText titleEt;
    private EditText descriptionEt;
    private EditText addressEt;
    private EditText priceEt;
    private EditText emailEt;
    private EditText phoneEt;

    RadioGroup categoryRg;

    String title, description, address, price, category, username, email, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sharedPref = getSharedPreferences(FixedVars.PREF_NAME, Context.MODE_PRIVATE);
        titleEt = (EditText) findViewById(R.id.titleEt);
        descriptionEt = (EditText) findViewById(R.id.descriptionEt);
        addressEt = (EditText) findViewById(R.id.addressEt);
        priceEt = (EditText) findViewById(R.id.priceEt);
        emailEt = (EditText) findViewById(R.id.emailEt);
        phoneEt = (EditText) findViewById(R.id.phoneEt);

        categoryRg = (RadioGroup) findViewById(R.id.categoryRg);

        username = sharedPref.getString(FixedVars.PREF_USER_NAME, "");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void sellSaveButtonClicked(View view) {

        if (categoryRg.getCheckedRadioButtonId() != -1) {
            category = ((RadioButton) findViewById(categoryRg.getCheckedRadioButtonId())).getText().toString();
        }

        title = titleEt.getText().toString();
        description = descriptionEt.getText().toString();
        address = addressEt.getText().toString();
        price = priceEt.getText().toString();
        email = emailEt.getText().toString();
        phone = phoneEt.getText().toString();

        /*Toast.makeText(this, title,Toast.LENGTH_SHORT).show();
        Toast.makeText(this, description,Toast.LENGTH_SHORT).show();
        Toast.makeText(this, address,Toast.LENGTH_SHORT).show();
        Toast.makeText(this, price,Toast.LENGTH_SHORT).show();
        Toast.makeText(this, category,Toast.LENGTH_SHORT).show();
        Toast.makeText(this, username,Toast.LENGTH_SHORT).show();
        Toast.makeText(this, email,Toast.LENGTH_SHORT).show();
        Toast.makeText(this, phone,Toast.LENGTH_SHORT).show();*/

        try {
            Boolean a = new SellAddInfo().execute(title,description,address,price,category,username,email,phone).get();
            if (a) {
                Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        startActivity(new Intent(this, SellActivity.class));
    }

    public void sellCancelButtonClicked(View view) {
        finish();
    }
}