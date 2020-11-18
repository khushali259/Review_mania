package com.example.reviewmanager;

import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Review> {

    private int resourceLayout;
    private Context mContext;

    public ListAdapter(Context context, int resource, List<Review> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);
        }

        Review p = getItem(position);

        if (p != null) {
            TextView reviewText = (TextView) v.findViewById(R.id.reviewText);
            RatingBar ratingBar = v.findViewById(R.id.rating);

            if (reviewText != null) {
                reviewText.setText(p.getReview());
            }

            if (ratingBar != null) {
                ratingBar.setRating(p.getRating());
            }

        }

        return v;
    }

}
