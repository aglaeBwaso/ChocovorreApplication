package com.example.chocovorre.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.chocovorre.R;
import com.example.chocovorre.adapters.RecyclerViewAdapter;
import com.example.chocovorre.model.Choco;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ListActivity extends AppCompatActivity {

    private String JSON_URL = "https://gist.githubusercontent.com/aglaeBwaso/1d251e1db076aef28781f0eb1b49501a/raw/898deb8c40fc89efe019d8a73000bb0d10830993/Chocovore.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Choco> lstChoco;
    private RecyclerView recyclerView;

    //public final static String ID_EXTRA =


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lstChoco = new ArrayList<>();

        recyclerView = findViewById(R.id.leRecyclerView);
        JsonRequest();

    }

    private void JsonRequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        Choco choco = new Choco();
                        choco.setTitle(jsonObject.getString("title"));
                        choco.setOrigin(jsonObject.getString("origin"));
                        choco.setPercentage(jsonObject.getInt("percentage"));
                        choco.setComposition(jsonObject.getString("composition"));
                        choco.setImage_url(jsonObject.getString("image_url"));
                        choco.setPrice(jsonObject.getInt("price"));

                        lstChoco.add(choco);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //ListView myList = (ListView) findViewById(R.id.myListView);

                Toast.makeText(ListActivity.this, "Size of list " + String.valueOf(lstChoco.size()), Toast.LENGTH_SHORT).show();
                Toast.makeText(ListActivity.this, lstChoco.get(1).toString(), Toast.LENGTH_SHORT).show();

                setRecyclerAdapter(lstChoco);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(ListActivity.this);
        requestQueue.add(request);


    }//Ca marche

    private void setRecyclerAdapter(List<Choco> lst) {

        RecyclerViewAdapter MyAdapter = new RecyclerViewAdapter(this, lst);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(MyAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.home) {
            Intent intent = new Intent(this,ListActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
