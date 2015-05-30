package com.app.hindu.ui;

import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.app.hindu.R;
import com.app.hindu.adapters.DetailPagerAdapter;
import com.app.hindu.application.GlobalObject;
import com.app.hindu.background.FetchDetails;
import com.app.hindu.constants.HTTPConstants;
import com.app.hindu.model.News;
import com.joanzapata.android.iconify.Iconify;

import java.util.List;
import java.util.Locale;


public class NewsDetailActivity extends ActionBarActivity implements TextToSpeech.OnInitListener{

    DetailPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;
    public static String PARAM_SLIDE_INDEX = "PARAM_SLIDE_INDEX";
    public static String CONTENT_TYPE = "CONTENT_TYPE";
    public List<News> shareContent;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news__detail);

        tts = new TextToSpeech(this, this);
        tts.setSpeechRate(0.8F);
        tts.setPitch(0.7F);
        tts.setLanguage(Locale.ENGLISH);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        //Set title

        //Get the list index that is selected
        Bundle parcel = getIntent().getExtras();

        int slide_index = parcel.getInt(PARAM_SLIDE_INDEX);
        int index = parcel.getInt(CONTENT_TYPE);

        String url = "";
        String title="";
        if(index == 0 ){
            url = HTTPConstants.HOME_SERVICE_FEED;
            title = getString(R.string.title_section0);
        } else if(index == 1){
            url = HTTPConstants.NEWS_SERVICE_FEED;
            title = getString(R.string.title_section1);
        } else if(index == 2){
            url = HTTPConstants.OPINION_SERVICE_FEED;
            title = getString(R.string.title_section2);
        } else if(index == 3){
            url = HTTPConstants.BUSINESS_SERVICE_FEED;
            title = getString(R.string.title_section3);
        } else if(index == 4){
            url = HTTPConstants.SPORT_SERVICE_FEED;
            title = getString(R.string.title_section4);
        } else if(index == 5){
            url = HTTPConstants.NEWS_SERVICE_FEED;
            title = getString(R.string.title_section5);
        }

        final String pageTitle = title;
        //Try to fetch data from Global Object
        final GlobalObject globalVariable = (GlobalObject) getApplicationContext();
        final List<News> articles = globalVariable.getArticles();
        mViewPager = (ViewPager) findViewById(R.id.pager);


        if(articles != null || articles.size() == 0){
            //Cached Object
            mCustomPagerAdapter = new DetailPagerAdapter(getSupportFragmentManager(), this,articles);
            mViewPager.setAdapter(mCustomPagerAdapter);
            mViewPager.setCurrentItem(slide_index);
            String currentSlideTitle = pageTitle + " ( " + (mViewPager.getCurrentItem() + 1) + "/" + (articles.size()) + " ) ";
            getSupportActionBar().setTitle(currentSlideTitle);

            //On page change
            mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    String currentSlideTitle = pageTitle + " ( " + (mViewPager.getCurrentItem() + 1) + "/" + (articles.size()) + " ) ";
                    getSupportActionBar().setTitle(currentSlideTitle);
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            shareContent = articles;
        }else{

            FetchDetails detailsTask = new FetchDetails(this,getSupportFragmentManager(),mViewPager);
            detailsTask.execute(url);
            mViewPager.setCurrentItem(slide_index);
            String currentSlideTitle = pageTitle + " ( " + (mViewPager.getCurrentItem() + 1) + "/" + (articles.size()) + " ) ";
            getSupportActionBar().setTitle(currentSlideTitle);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news__detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, shareContent.get(mViewPager.getCurrentItem()).getDesc());
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Send"));
        }
        if( id == R.id.action_read){
            Toast.makeText(this,"Reading..",Toast.LENGTH_SHORT).show();
            speakOut();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
               // speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

    private void speakOut() {

        tts.speak(shareContent.get(mViewPager.getCurrentItem()).getDesc(), TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}

