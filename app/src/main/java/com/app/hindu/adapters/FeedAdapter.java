package com.app.hindu.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.hindu.ui.NewsDetailActivity;
import com.app.hindu.R;
import com.app.hindu.model.News;
import com.app.hindu.util.SpeedScrollListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Adapter
 * Created by Srihari Surabhi
 */
public class FeedAdapter  extends RecyclerView.Adapter<FeedAdapter.ViewHolder> implements View.OnClickListener {

    private List<News> news;
    private Context mContext;
    private static int content_type;

    /**
     * Provide a reference to the type of views that we are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView articleTitle;
        private final ImageView articleImage;

        public ViewHolder(View v) {
            super(v);

            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("Clicked","Element " + getPosition() + " clicked.");

                    Intent intent = new Intent(v.getContext(), NewsDetailActivity.class);
                    intent.putExtra(NewsDetailActivity.PARAM_SLIDE_INDEX, getPosition());
                    intent.putExtra(NewsDetailActivity.CONTENT_TYPE,content_type);

                    v.getContext().startActivity(intent);
                }
            });

            articleTitle = (TextView) v.findViewById(R.id.title);
            articleImage = (ImageView) v.findViewById(R.id.news_image);
        }

        public TextView getTitleTextView() { return articleTitle; }
        public ImageView getIconImageView() { return articleImage; }
    }

    public FeedAdapter(Context context,
                       List<News> news_list,
                       int content_type_selected
                       ) {

        mContext = context;
        news = news_list;
        content_type = content_type_selected;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.feed_row, viewGroup, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.getTitleTextView().setText(news.get(position).getTitle());
        if(news.get(position).getArticleImg() != null && news.get(position).getArticleImg().length() > 0){
            Picasso.with(mContext)
                    .load(news.get(position).getArticleImg())
                    .placeholder(R.drawable.default_image).error(R.drawable.default_image)
                    .into(viewHolder.getIconImageView());

        }
    }

    // Return the size of dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return news.size();
    }

    @Override
    public void onClick(View v) {
    }
}

