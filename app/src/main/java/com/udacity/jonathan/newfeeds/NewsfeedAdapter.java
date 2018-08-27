package com.udacity.jonathan.newfeeds;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class NewsfeedAdapter extends ArrayAdapter<Newsfeed> {

    public NewsfeedAdapter(Context context, List<Newsfeed> newsfeeds){
        super(context, 0, newsfeeds);
    }






}
