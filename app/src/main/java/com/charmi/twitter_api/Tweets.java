package com.charmi.twitter_api;

/**
 * Created by ADMIN on 8/1/2017.
 */

public class Tweets {

    String mtime, mtext;

    public Tweets(String time, String text) {
        mtime = time;
        mtext = text;
    }

    public String getMtime() {
        return mtime;
    }

    public String getMtext() {
        return mtext;
    }
}
