package com.project.rushabh.buildomania;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Rushabh on 10-Oct-17.
 */

public class SellAddInfo extends AsyncTask<String,Void,Boolean> {
    @Override
    protected Boolean doInBackground(String... params) {
        try {
            String title,description,address,price,category,username,email,phone;
            title = params[0];
            description = params[1];
            address=params[2];
            price=params[3];
            category=params[4];
            username=params[5];
            email=params[6];
            phone=params[7];


            String link = FixedVars.LINK+"selladdinfo.php";
            String data;
            data = URLEncoder.encode("title", "UTF-8") + "=" +
                    URLEncoder.encode(title, "UTF-8");
            data += "&" +URLEncoder.encode("description", "UTF-8") + "=" +
                    URLEncoder.encode(description, "UTF-8");
            data += "&" +URLEncoder.encode("address", "UTF-8") + "=" +
                    URLEncoder.encode(address, "UTF-8");
            data += "&" +URLEncoder.encode("price", "UTF-8") + "=" +
                    URLEncoder.encode(price, "UTF-8");
            data += "&" +URLEncoder.encode("category", "UTF-8") + "=" +
                    URLEncoder.encode(category, "UTF-8");
            data += "&" +URLEncoder.encode("username", "UTF-8") + "=" +
                    URLEncoder.encode(username, "UTF-8");
            data += "&" +URLEncoder.encode("email", "UTF-8") + "=" +
                    URLEncoder.encode(email, "UTF-8");
            data += "&" +URLEncoder.encode("phone", "UTF-8") + "=" +
                    URLEncoder.encode(phone, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            return reader != null;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
