package com.app.hindu.background;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.hindu.R;
import com.app.hindu.adapters.FeedAdapter;
import com.app.hindu.application.GlobalObject;
import com.app.hindu.constants.HTTPConstants;
import com.app.hindu.model.News;
import com.app.hindu.util.ConnectionUtil;
import com.app.hindu.util.HttpUtil;
import com.app.hindu.util.XMLParser;

import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;

import java.util.List;


public class FetchFeed extends AsyncTask<String, Void, String> {

    private Context context;
    public JSONArray data;
    public ProgressBar bar;
    private Activity activity;
    private TextView message;
    private String url;
    private FeedAdapter mSpeakersAdapter;
    private RecyclerView mSpeakerListRecyclerView;
    private LinearLayoutManager mSpeakerLayoutManager;
    private int content_type = 0;

    public FetchFeed(Context context,
                     ProgressBar bar,
                     Activity activity,
                     TextView message,
                     String url,
                     RecyclerView recyclerView,
                     LinearLayoutManager layoutManager,
                     int content_type
                        ){

        this.context = context;
        this.activity = activity;
        this.bar = bar;
        this.message = message;
        this.url = url;
        this.mSpeakerLayoutManager = layoutManager;
        this.mSpeakerListRecyclerView = recyclerView;
        this.content_type = content_type;
    }


    @Override
    protected String doInBackground(String... params) {

        HttpClient client = new DefaultHttpClient();
        String data="";
        try {

            if(ConnectionUtil.isNetworkAvailable(context)){
                HttpGet get = new HttpGet(url);
                get.addHeader(BasicScheme.authenticate(
                        new UsernamePasswordCredentials(
                                HTTPConstants.FEED_USERNAME,
                                HTTPConstants.FEED_PASSWORD),
                        "UTF-8", false));
                HttpResponse response = client.execute(get);
                data = HttpUtil.reader(response);
            }
            else{
                data = "NetworkFailure";
            }
        } catch (Exception e) {
            data = "NetworkFailure";
            //e.printStackTrace();

        }
        return data;
    }

    @Override
    protected void onPostExecute(String data) {
        super.onPostExecute(data);
        try {
            bar.setVisibility(View.INVISIBLE);
            if(data != null){

                if(data.equals("NetworkFailure")){
                    message.setText(context.getString(R.string.network_failure));
                    message.setVisibility(View.VISIBLE);
                }
                List<News> news = XMLParser.parseXMLAndStoreIt(data);
                //Store the data
                // Calling Application class (see application tag in AndroidManifest.xml)
                final GlobalObject globalVariable = (GlobalObject) context.getApplicationContext();

                //Set name and email in global/application context
                globalVariable.setArticles(news);

                if(news.size() == 0){
                   //view.setVisibility(View.VISIBLE);
                }
                mSpeakersAdapter = new FeedAdapter(context,news,content_type);
                mSpeakerListRecyclerView.setAdapter(mSpeakersAdapter);

            }else{
                //Server Did not respond with data
                message.setText("No News");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

