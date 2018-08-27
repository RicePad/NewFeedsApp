package com.udacity.jonathan.newfeeds;

public class Newsfeed {

    /** NewssFeed Tittle */
    private String mTitle;

    /** Website URL of the NewsFeed */
    private String mUrl;

    public Newsfeed(String title, String url) {
        mTitle = title;
        mUrl = url;
    }

    public String getNewsfeedTitle(){
        return mTitle;
    }

    public String getUrl(){
        return mUrl;
    }

}
