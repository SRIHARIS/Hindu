package com.app.hindu.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.hindu.model.News;
import com.app.hindu.ui.CompleteArticleFragment;

import java.util.List;

/**
 * Created by 914893 on 5/26/15.
 */
public class DetailPagerAdapter extends FragmentStatePagerAdapter {

    protected Context mContext;
    protected List<News> news;

    public DetailPagerAdapter(FragmentManager fm, Context context,List<News> news) {
        super(fm);
        mContext = context;
        this.news = news;
    }

    @Override
    // This method returns the fragment associated with
    // the specified position.
    //
    // It is called when the Adapter needs a fragment
    // and it does not exists.
    public Fragment getItem(int position) {

        // Create fragment object
        Fragment fragment = new CompleteArticleFragment();

        // Attach some data to it that we'll
        // use to populate our fragment layouts
        Bundle args = new Bundle();
        args.putInt("page_position", position + 1);
        args.putString("title",news.get(position).getTitle());
        args.putString("desc",news.get(position).getDesc());
        args.putString("link",news.get(position).getLink());
        args.putString("img",news.get(position).getArticleImg());
        // Set the arguments on the fragment
        // that will be fetched in DemoFragment@onCreateView
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public int getCount() {
        return news.size();
    }
}