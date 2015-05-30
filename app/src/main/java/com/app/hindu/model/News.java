package com.app.hindu.model;

/**
 * Created by Srihari on 5/26/15.
 */
public class News {

    private String title;
    private String desc;
    private String priority;
    private String teaserTitle;
    private String link;
    private String guid;
    private String atype;
    private String publishDate;
    private Boolean breakingNews;
    private String articleImg;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTeaserTitle() {
        return teaserTitle;
    }

    public void setTeaserTitle(String teaserTitle) {
        this.teaserTitle = teaserTitle;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getAtype() {
        return atype;
    }

    public void setAtype(String atype) {
        this.atype = atype;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public Boolean getBreakingNews() {
        return breakingNews;
    }

    public void setBreakingNews(Boolean breakingNews) {
        this.breakingNews = breakingNews;
    }

    public String getArticleImg() {
        return articleImg;
    }

    public void setArticleImg(String articleImg) {
        this.articleImg = articleImg;
    }
}
