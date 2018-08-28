package com.udacity.jonathan.newfeeds;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<Newsfeed>> {

    private static final String LOG_TAG = MainActivity.class.getName();

    /** URL for guardianAPI data from the Guardian dataset */
    private static final String GUARDIAN_REQUEST_URL =
            "https://content.guardianapis.com/football?api-key=5406cfd9-9ac5-4e7e-8e7c-38a5c1537b86";

    /**
     * Constant value for the newsfeed loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int NEWSFEED_LOADER_ID = 1;

    /** Adapter for the list of newsfeed */
    private NewsfeedAdapter mAdapter;

//    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView newsfeedListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsfeedListView.setEmptyView(mEmptyStateTextView);

        // Create a new adapter that takes an empty list of newsfeed as input
        mAdapter = new NewsfeedAdapter(this, new ArrayList<Newsfeed>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        newsfeedListView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected newsfeed.
        newsfeedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current newsfeed that was clicked on
                Newsfeed currentNewsfeed = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsfeedUri = Uri.parse(currentNewsfeed.getUrl());

                // Create a new intent to view the newsfeed URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsfeedUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(NEWSFEED_LOADER_ID, null, this);
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText("no internet connection");
        }
    }

    @Override
    public Loader<List<Newsfeed>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsfeedLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Newsfeed>> loader, List<Newsfeed> newsfeeds) {
        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        // Set empty state text to display "No newsfeed found."
        mEmptyStateTextView.setText("No Newsfeeds");

        // Clear the adapter of previous Newsfeed data
        mAdapter.clear();

        // If there is a valid list of {@link Newsfeeds}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (newsfeeds != null && !newsfeeds.isEmpty()) {
            mAdapter.addAll(newsfeeds);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Newsfeed>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }
}
