package com.udacity.jonathan.newfeeds;

public class Newsfeed {

    /** NewssFeed Tittle */
    private String mTitle;

    /** Website URL of the NewsFeed */
    private String mUrl;

    /** Section to which the newsfeed belongs */
    private String mSection;

    /** Publication date of the newsfeed */
    private long mDateTime;

    public Newsfeed(String title, String url, String section, long dateTime) {
        mTitle = title;
        mUrl = url;
        mSection = section;
        mDateTime = dateTime;
    }

    public String getNewsfeedTitle(){
        return mTitle;
    }

    public String getUrl(){
        return mUrl;
    }

    public String getSection(){
        return mSection;
    }

    public long getDateTime(){
        return mDateTime;
    }

}
