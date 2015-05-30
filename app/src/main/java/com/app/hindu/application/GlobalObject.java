package com.app.hindu.application;

import android.app.Application;

import com.app.hindu.model.News;

import java.util.List;

/**
 * Created by Srihari on 5/27/15.
 */
public class GlobalObject extends Application {

    private List<News> articles;

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }
}
