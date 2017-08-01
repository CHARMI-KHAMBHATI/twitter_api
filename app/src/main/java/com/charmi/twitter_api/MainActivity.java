package com.charmi.twitter_api;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Tweets>> {

    private static final int LOADER_ID = 1;
    private static String REQUEST_URL ="https://loklak.org/api/search.json?q=";
    private EditText user_id;

    private ListView tweetListView;
    private TweetAdapter mAdapter;

    private ProgressBar progress;
    private TextView empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_id = (EditText) findViewById(R.id.user_id);
        tweetListView = (ListView) findViewById(R.id.list);

        empty= (TextView) findViewById(R.id.empty);
        progress= (ProgressBar) findViewById(R.id.loading_spinner);


    }

    public void submit(View v){


        REQUEST_URL = REQUEST_URL + user_id.getText().toString();
        //Toast.makeText(this, "clicked - "+ REQUEST_URL, Toast.LENGTH_SHORT).show();


        mAdapter = new TweetAdapter(this, new ArrayList<Tweets>());

        tweetListView.setEmptyView(findViewById(R.id.empty));

        tweetListView.setAdapter(mAdapter);


        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);


        } else {

            progress.setVisibility(View.INVISIBLE);
            empty.setText("No Internet Connection");
        }
    }

    @Override
    public Loader<List<Tweets>> onCreateLoader(int id, Bundle bundle) {
        return new TweetLoader(this, REQUEST_URL);
    }



    @Override
    public void onLoadFinished(Loader<List<Tweets>> loader, final List<Tweets> tweets) {


        progress.setVisibility(View.INVISIBLE);
        mAdapter.clear();
        empty.setText("No Earthquakes found");

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (tweets != null && !tweets.isEmpty()) {

            mAdapter.addAll(tweets);

        }



    }



    @Override
    public void onLoaderReset(Loader<List<Tweets>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();

    }
}
