package com.app.hindu.background;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.app.hindu.adapters.DetailPagerAdapter;
import com.app.hindu.constants.HTTPConstants;
import com.app.hindu.model.News;
import com.app.hindu.util.HttpUtil;
import com.app.hindu.util.XMLParser;

import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;


public class FetchDetails extends AsyncTask<String, Void, String> {

    private Context context;
    DetailPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    FragmentManager fragmentManager;

    public FetchDetails(Context context,FragmentManager fm,ViewPager pager){
        this.context = context;
        this.fragmentManager = fm;
        this.mViewPager = pager;
    }


    @Override
    protected String doInBackground(String... params) {

        HttpClient client = new DefaultHttpClient();
        String data = "";
        try {
            HttpGet get = new HttpGet(params[0]);
            get.addHeader(BasicScheme.authenticate(
                    new UsernamePasswordCredentials(
                            HTTPConstants.FEED_USERNAME,
                            HTTPConstants.FEED_PASSWORD),
                    "UTF-8", false));

            HttpResponse response = client.execute(get);
            data = HttpUtil.reader(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    protected void onPostExecute(String data) {
        super.onPostExecute(data);
        try {
            if(data != null){
                List<News> news = XMLParser.parseXMLAndStoreIt(data);

                mCustomPagerAdapter = new DetailPagerAdapter(fragmentManager, context,news);
                mViewPager.setAdapter(mCustomPagerAdapter);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

