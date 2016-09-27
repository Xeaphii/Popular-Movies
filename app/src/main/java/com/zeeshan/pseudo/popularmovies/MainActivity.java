package com.zeeshan.pseudo.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final List<MoviePOJO> mItems = new ArrayList<MoviePOJO>();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://api.themoviedb.org/3/movie/popular?api_key=4a66889d6edc783c623d0f8e45e57bee";

        Toast.makeText(getApplicationContext(),"Start making request",Toast.LENGTH_LONG).show();
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(getApplicationContext(),"Resp length is "+response.length(),Toast.LENGTH_LONG).show();

                        JSONObject jsonObj = null;
                        JSONArray jsonArray = null;

                        try {
                            jsonObj = new JSONObject(response);
                            jsonArray = jsonObj.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject explrObject = jsonArray.getJSONObject(i);
                                String title = explrObject.getString("title");
                                String poster_path = explrObject.getString("poster_path");
                                String original_title = explrObject.getString("original_title");
                                String release_date = explrObject.getString("release_date");
                                String overview = explrObject.getString("overview");
                                String vote_average = explrObject.getString("vote_average");

                                mItems.add(new MoviePOJO(title, "http://image.tmdb.org/t/p/w185//" + poster_path, original_title, overview, vote_average, release_date));

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        GridView gridView = (GridView) findViewById(R.id.gridview);
                        gridView.setAdapter(new MyAdapter(MainActivity.this,mItems));


                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View v,
                                                    int position, long id) {

                                // DO something

                                MoviePOJO item = mItems.get(position);

                                Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
                                myIntent.putExtra("url", item.url); //Optional parameters
                                myIntent.putExtra("orig_title", item.orig_title); //Optional parameters
                                myIntent.putExtra("movie_plot", item.movie_plot); //Optional parameters
                                myIntent.putExtra("movie_rating", item.movie_rating); //Optional parameters
                                myIntent.putExtra("release_date", item.release_date); //Optional parameters
                                MainActivity.this.startActivity(myIntent);


                            }
                        });


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Failed to Load data", Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);




    }


}
