package com.example.chocovorre.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.chocovorre.R;

public class DescriptionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showoneitem);

        //hide default actions
        //getSupportActionBar().hide();

        //Receive data
        String compo = getIntent().getExtras().getString("composition");
        String titre = getIntent().getExtras().getString("title");
        String logo = getIntent().getExtras().getString("image_url");

        //Init views

        TextView description = findViewById(R.id.description_choco);
        TextView title = findViewById(R.id.titre);
        ImageView iv_logo = findViewById(R.id.imagevi);

        //setting values to each view

        description.setText(compo);
        title.setText(titre);
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        //setting image using Glide

        Glide.with(this).load(logo).apply(requestOptions).into(iv_logo);
    }

}
