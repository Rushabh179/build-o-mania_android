package com.project.rushabh.buildomania;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.neel.articleshubapi.restapi.beans.UserDetail;
import com.neel.articleshubapi.restapi.request.HeaderTools;
import com.neel.articleshubapi.restapi.request.RequestTask;

import org.springframework.http.HttpMethod;

import static com.neel.articleshubapi.restapi.request.HeaderTools.CONTENT_TYPE_JSON;

public class UserProfile extends AppCompatActivity {

    //Declaring SharedPreferences to access data from other login activity
    public SharedPreferences sharedPref;
    Toolbar toolbar = null;
    TextView profileHeading;
    TextView userEmailID;
    TextView userFname;
    TextView userLname;
    TextView userInfo;
    TextView uname;
    Button editDetailButton;
    Button logoutButton;
    String token = null, userName = null;
    String ufname = null, ulname = null, uinfo = null, uemailid = null;
    UserDetail ud;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Getting data from SharedPreferences
        sharedPref = getSharedPreferences(FixedVars.PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        userName = sharedPref.getString(FixedVars.PREF_USER_NAME, "");
        token = sharedPref.getString(FixedVars.PREF_LOGIN_TOKEN, "");

        //Checking if the user has a profile or not
        if (token.isEmpty()) {
            Toast.makeText(UserProfile.this, "You need to be a logged in user to access your profile page", Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(UserProfile.this, LoginActivity.class);
            startActivity(myIntent);
        } else {

            //Checking for internet connection
            if (NetworkStatus.getInstance(this).isOnline()) {
                setContentView(R.layout.activity_user_profile);

                toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);

                //Below is the original code for displaying content on HomeProfilePage
                profileHeading = (TextView) findViewById(R.id.text_profile_page_heading);
                uname = (TextView) findViewById(R.id.text_profile_page_uname);
                userEmailID = (TextView) findViewById(R.id.text_profile_page_emailid);
                userFname = (TextView) findViewById(R.id.text_profile_page_userfname);
                userLname = (TextView) findViewById(R.id.text_profile_page_userlname);
                userInfo = (TextView) findViewById(R.id.text_profile_page_userinfo);
                editDetailButton = (Button) findViewById(R.id.btn_edit_detail);
                logoutButton = (Button) findViewById(R.id.btn_logout);

                RequestTask<UserDetail> rt =
                        new RequestTask<>(UserDetail.class, HttpMethod.GET, CONTENT_TYPE_JSON);
                rt.execute(FixedVars.BASE_URL + "/user/" + userName);
                // initiate waiting logic
                ud = rt.getObj();
                // terminate waiting logic

                uname.setText(userName);
                ufname = ud.getFirstName();
                ulname = ud.getLastName();
                uinfo = ud.getInfo();
                uemailid = ud.getEmailId();

                profileHeading.setText("" + ufname + "'s Profile");
                userEmailID.setText(uemailid);
                userFname.setText(ufname);
                userLname.setText(ulname);
                userInfo.setText(uinfo);

                editDetailButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent myIntent = new Intent(UserProfile.this, EditProfile.class);
                        startActivity(myIntent);
                        finish();
                    }
                });
                logoutButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        RequestTask<String> logoutRequest = new RequestTask<>(String.class, HttpMethod.DELETE,
                                new HeaderTools.EntryImp("token", token));
                        logoutRequest.execute(FixedVars.BASE_URL + "/authentication/" + ud.getUserName());
                        editor.clear();
                        editor.apply();
                        //listSelected.clear();
                        Intent myIntent = new Intent(UserProfile.this, LoginActivity.class);
                        startActivity(myIntent);
                        Snackbar.make(view, "You have successfully logged out!!!", Snackbar.LENGTH_LONG).show();
                        finish();
                    }
                });
            } else
                NetworkStatus.getInstance(this).buildDialog(this).show();
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}