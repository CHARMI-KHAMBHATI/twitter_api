package com.charmi.twitter_api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ADMIN on 8/1/2017.
 */

class TweetAdapter extends ArrayAdapter<Tweets>
{
    public TweetAdapter(Context context, List<Tweets> tweets) {
        super(context, 0, tweets);
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        View ListItemView= convertView;
        if(ListItemView==null)
        {
            ListItemView= LayoutInflater.from(getContext()).inflate(R.layout.tweet_list_activity, parent,false);
        }

        Tweets currentTweet= getItem(position);
        TextView text = (TextView) ListItemView.findViewById(R.id.text);
        text.setText(currentTweet.getMtext());

        TextView time = (TextView) ListItemView.findViewById(R.id.time);
        time.setText(currentTweet.getMtime());


        return ListItemView;
    }
}
