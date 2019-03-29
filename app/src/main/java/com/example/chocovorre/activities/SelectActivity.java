package com.example.chocovorre.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.chocovorre.R;
import com.example.chocovorre.adapters.RecyclerViewAdapter;
import com.example.chocovorre.adapters.RecyclerViewAdapter_selection;
import com.example.chocovorre.model.Choco;
import com.example.chocovorre.model.DescriptModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SelectActivity extends AppCompatActivity {

    private String JSON_URL = "https://gist.githubusercontent.com/aglaeBwaso/1d251e1db076aef28781f0eb1b49501a/raw/898deb8c40fc89efe019d8a73000bb0d10830993/Chocovore.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<DescriptModel> lstChocoDescript;
    private RecyclerView recyclerView;

    //public final static String ID_EXTRA =


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        lstChocoDescript = new ArrayList<>();

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
                        DescriptModel descriptModel = new DescriptModel();
                        descriptModel.setComposition(jsonObject.getString("composition"));
                        lstChocoDescript.add(descriptModel);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setRecyclerAdapter(lstChocoDescript);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(SelectActivity.this);
        requestQueue.add(request);


    }//Ca marche

    private void setRecyclerAdapter(List<DescriptModel> lstdescriptModel) {

        RecyclerViewAdapter_selection MyAdapter = new RecyclerViewAdapter_selection(this, lstdescriptModel);
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


    /*private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent,
                                View view, int position,
                                long id) {
            //create our intent, as per usual
            Intent i = new Intent(ListActivity.this, SelectOneItem.class);

            i.putExtra(JSON_URL, String.valueOf(id));
            startActivity(i);


        }
    };*/
