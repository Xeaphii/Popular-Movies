package com.zeeshan.pseudo.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pseudo on 9/26/2016.
 */
  class MyAdapter extends BaseAdapter {
    private final List<Item> mItems = new ArrayList<Item>();
    private final LayoutInflater mInflater;
    private Context mContext;

    public MyAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mContext = context;


        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://api.themoviedb.org/3/movie/popular?api_key=4a66889d6edc783c623d0f8e45e57bee";


// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.


                        JSONObject jsonObj = null;
                        JSONArray jsonArray = null;

                        try {
                            jsonObj = new JSONObject(response);
                            jsonArray = jsonObj.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject explrObject = jsonArray.getJSONObject(i);
                                String title = explrObject.getString("title");
                                String poster_path = explrObject.getString("poster_path");

                                mItems.add(new Item(title,   "http://image.tmdb.org/t/p/w185//"+    poster_path));
                                //Toast.makeText(mContext, "http://image.tmdb.org/t/p/w185//"+    poster_path, Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(mContext,"Failed to Load data",Toast.LENGTH_LONG).show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(stringRequest);



    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Item getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return  Long.valueOf(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView picture;
        TextView name;

        if (v == null) {
            v = mInflater.inflate(R.layout.grid_item, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView) v.getTag(R.id.picture);
        name = (TextView) v.getTag(R.id.text);



        Item item = getItem(i);

        //picture.setImageResource(item.drawableId);
        Picasso.with(mContext).load(item.url).into(picture);

        name.setText(item.name);

        return v;
    }

    private static class Item {
        public final String name;
        public final String url;

        Item(String name, String URL) {
            this.name = name;
            this.url = URL;
        }
    }
}