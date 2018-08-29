package com.udacity.jonathan.newfeeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        TextView newsfeedSectionTextView = (TextView) listItemView.findViewById(R.id.newsfeed_section_text_view);
        TextView newsfeedDateTextView = (TextView) listItemView.findViewById(R.id.newsfeed_date);



        // Get the NewsfeedTitle from the currentNewsfeed object and set this text on
        // the TextView.
        newsfeedTextView.setText(currentNewsfeed.getNewsfeedTitle());
        newsfeedSectionTextView.setText(currentNewsfeed.getSection());

        // Create a new Date object from the time in milliseconds of the earthquake
        Date dateObject = new Date(currentNewsfeed.getDateTime());

        // Find the TextView with view ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.newsfeed_date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);


        return listItemView;

    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a Date object.
     */
    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }
}
