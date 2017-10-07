package com.project.rushabh.buildomania;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rushabh on 07-Oct-17.
 */

public class BuyCustomAdapter  extends ArrayAdapter<String> {

    BuyCustomAdapter(@NonNull Context context, String[] names) {
        super(context, R.layout.buy_custom_row, names);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder")
        View customView = inflater.inflate(R.layout.buy_custom_row,parent,false);

        String getSingleItem = getItem(position);
        //ImageView appImageView = (ImageView) customView.findViewById(R.id.appImageView);
        TextView appTvName = (TextView) customView.findViewById(R.id.buyTitle);

        //appImageView.setImageResource(R.drawable.ic_lightbulb);
        appTvName.setText(getSingleItem);

        return customView;
    }
}