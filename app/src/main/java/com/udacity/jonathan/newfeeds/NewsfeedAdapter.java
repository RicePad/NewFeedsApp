package com.udacity.jonathan.newfeeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NewsfeedAdapter extends ArrayAdapter<Newsfeed> {

    public NewsfeedAdapter(Context context, List<Newsfeed> newsfeeds){
        super(context, 0, newsfeeds);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.new_list_item, parent, false);
        }

        // Get the {@link Newsfeed} object located at this position in the list
        Newsfeed currentNewsfeed = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID attraction_text_view.
        TextView newsfeedTextView = (TextView) listItemView.findViewById(R.id.title_location_text_view);

        // Get the NewsfeedTitle from the currentNewsfeed object and set this text on
        // the TextView.
        newsfeedTextView.setText(currentNewsfeed.getNewsfeedTitle());

        return listItemView;

    }}
