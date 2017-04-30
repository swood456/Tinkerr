package com.example.stephen.androidrater;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephen on 4/21/2017.
 */

public class ElementAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<DatabaseElement> mDataSource;

    public ElementAdapter(Context context, List<DatabaseElement> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //1
    @Override
    public int getCount() {
        return mDataSource.size();
    }

    //2
    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    //3
    @Override
    public long getItemId(int position) {
        return position;
    }

    //4
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get view for row item
        View rowView = mInflater.inflate(R.layout.element_listview_display, parent, false);

        // Get name element
        TextView titleTextView =
                (TextView) rowView.findViewById(R.id.element_display_name);

// Get description element
        TextView subtitleTextView =
                (TextView) rowView.findViewById(R.id.element_display_description);

// Get rating element
        RatingBar detailRatingBar =
                (RatingBar) rowView.findViewById(R.id.ratingBar_element_rating);

        // 1
        DatabaseElement element = (DatabaseElement) getItem(position);

// 2
        titleTextView.setText(element.get_name());
        subtitleTextView.setText(element.get_description());
        detailRatingBar.setRating(element.get_rating());
        Log.d("mine", "get rating is: " + element.get_rating());

// 3
        //Picasso.with(mContext).load(recipe.imageUrl).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);

        return rowView;
    }
}
