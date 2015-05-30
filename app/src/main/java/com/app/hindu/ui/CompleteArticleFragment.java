package com.app.hindu.ui;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.hindu.R;
import com.squareup.picasso.Picasso;


public class CompleteArticleFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_complete_article, container, false);

        Bundle args = getArguments();

        String title = args.getString("title");
        String desc = args.getString("desc");
        String img = args.getString("img");
        String publishDate = args.getString("pubDate");

        TextView title_node = (TextView)rootView.findViewById(R.id.title);
        TextView desc_node = (TextView)rootView.findViewById(R.id.description);
        TextView pub_node = (TextView)rootView.findViewById(R.id.publishDate);
        ImageView img_node = (ImageView) rootView.findViewById(R.id.article_image);

        title_node.setText(title);
        pub_node.setText(publishDate);
        desc_node.setText(desc);

        if(img != null && img.length() !=0 ){
            Picasso.with(this.getActivity())
                    .load(img)
                    .placeholder(R.drawable.default_image).error(R.drawable.default_image)
                    .into(img_node);

        }

        return rootView;
    }
}
