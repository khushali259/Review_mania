package com.example.reviewmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ViewReviewListAdapter extends ArrayAdapter<Movie> {

    private int resourceLayout;
    private Context mContext;

    public ViewReviewListAdapter(Context context, int resource, List<Movie> items) {
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

        Movie p = getItem(position);

        if (p != null) {
            TextView movieName = (TextView) v.findViewById(R.id.movieNameText);
            TextView overallRating = (TextView) v.findViewById(R.id.overallReviewText);

            if (movieName != null) {
                movieName.setText(p.getMovie_name());
            }

            if(overallRating!= null){
                String temp = "Overall ratings: " + p.getOverall_rating();
                overallRating.setText(temp);
            }


        }

        return v;
    }

}
