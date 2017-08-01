package com.charmi.twitter_api;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by ADMIN on 8/1/2017.
 */

class TweetLoader extends AsyncTaskLoader<List<Tweets>> {

    private  String mUrl;



    public TweetLoader(Context context, String url)
    {
        super(context);
        mUrl=url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Tweets> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        List<Tweets> tweets = Utils.fetchTweetData(mUrl);
        return tweets;
    }
}
