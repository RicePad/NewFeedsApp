package com.udacity.jonathan.newfeeds;

import android.content.AsyncTaskLoader;
import android.content.Context;
import java.util.List;


public class NewsfeedLoader extends AsyncTaskLoader<List<Newsfeed>> {
    /** Tag for log messages */
    private static final String LOG_TAG = NewsfeedLoader.class.getName();

    /** Query URL */
    private String mUrl;

    /**
     * Constructs a new {@link NewsfeedLoader}.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public NewsfeedLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }


    /**
     * This is on a background thread.
     */
    @Override
    public List<Newsfeed> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of newsfeeds.
        List<Newsfeed> newsfeeds = QueryUtils.fetchNewsfeedData(mUrl);
        return newsfeeds;
    }
}


