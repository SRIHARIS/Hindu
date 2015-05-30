package com.app.hindu.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by 914893 on 12/24/14.
 */
public class HttpUtil {

    /* Utility Method for converting response stream into string */
    public static String reader(final HttpResponse response) throws IOException {

        HttpEntity entity = response.getEntity();

        BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
        /*
        String line = reader.readLine();
        String data = " ";
        while (line != null) {
            if(data.equals(" "))
                data = line;
            line = reader.readLine();
        }
        */
        StringBuffer sb = new StringBuffer();
        String data="";
        String line = "";
        while( ( line = reader.readLine()) != null){
            sb.append(line);
        }

        data = sb.toString();
        reader.close();

        return data;
    }


}
