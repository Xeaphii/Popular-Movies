package com.zeeshan.pseudo.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pseudo on 9/26/2016.
 */
class MyAdapter extends BaseAdapter {
    private List<MoviePOJO> mItems = new ArrayList<MoviePOJO>();
    private final LayoutInflater mInflater;
    private Context mContext;

    public MyAdapter(Context context,List<MoviePOJO> mItemsList) {
        mInflater = LayoutInflater.from(context);
        mContext = context;
        this.mItems = mItemsList;
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public MoviePOJO getItem(int i) {
        return mItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.valueOf(i);
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


        MoviePOJO item = getItem(i);

        //picture.setImageResource(item.drawableId);
        Picasso.with(mContext).load(item.url).into(picture);

        name.setText(item.title);

        return v;
    }


}