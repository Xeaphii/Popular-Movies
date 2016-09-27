package com.zeeshan.pseudo.popularmovies;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Administrator on 9/27/2016.
 */
public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();
        String value = intent.getStringExtra("url"); //if it's a string you stored.

        Toast.makeText(getApplicationContext(),value,Toast.LENGTH_LONG).show();
    }
}
