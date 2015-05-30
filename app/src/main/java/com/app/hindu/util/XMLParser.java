package com.app.hindu.util;

import android.text.Html;

import com.app.hindu.model.News;

import org.apache.commons.lang3.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Srihari on 5/26/15.
 */
public class XMLParser {

    private static XmlPullParserFactory xmlFactoryObject;

    public static List<News> parseXMLAndStoreIt(String content) {


        int event;
        String text=null;
        ArrayList<News> articles = new ArrayList<News>();

        try {

            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser parser = xmlFactoryObject.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(new StringReader(content));

            event = parser.getEventType();
            News item = null;
            while (event != XmlPullParser.END_DOCUMENT) {
                String name=parser.getName();

                switch (event){
                    case XmlPullParser.START_TAG:
                        if(name.equals("item")){
                            item = new News();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(name.equals("title") && item != null){
                            item.setTitle(StringEscapeUtils.unescapeHtml4(text));
                        }
                        else if(name.equals("link") && item != null){
                            item.setLink(text);
                        }
                        else if(name.equals("description") && item != null){

                            //process desc
                            Document doc  = Jsoup.parse(text);

                            item.setDesc(doc.text());
                            Elements img = doc.select("img");
                            item.setArticleImg(img.attr("src"));
                        }
                        else if(name.equals("pubDate")){
                            item.setPublishDate(text);
                        }
                        else if(name.equals("item")){
                            articles.add(item);
                        }
                        break;
                }
                event = parser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }
}

