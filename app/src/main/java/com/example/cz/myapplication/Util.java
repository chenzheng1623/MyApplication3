package com.example.cz.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cz on 2015/6/19.
 */
public class Util {

    public static boolean checkNeywork(Context Context) {
        boolean wifiConnected;
        boolean mobileConnected;
        ConnectivityManager connManager = (ConnectivityManager) Context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeinfo = connManager.getActiveNetworkInfo();
        if (activeinfo != null && activeinfo.isConnected()) {
            wifiConnected = activeinfo.getType() == ConnectivityManager.TYPE_WIFI;
            mobileConnected = activeinfo.getType() == ConnectivityManager.TYPE_MOBILE;
            if (wifiConnected) {
                Toast.makeText(Context, "wifi connected", Toast.LENGTH_SHORT).show();
                return true;
            } else if (mobileConnected) {
                Toast.makeText(Context, "mobile connected", Toast.LENGTH_SHORT).show();
                return true;
            }
        } else {
            Toast.makeText(Context, "network notconnected", Toast.LENGTH_SHORT).show();
            return false;
        }

        return  false;
    }
    public  static void  httpconn(String urlString,HttpConnectionListener httplistener){

        try {
            URL url=new URL(urlString);
            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setConnectTimeout(15000);
            conn.setReadTimeout(10000);
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream stream=conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer=new StringBuffer();
            String content=null;

            while ((content=reader.readLine()) != null){
                buffer.append(content);
            }
            if (!TextUtils.isEmpty(buffer)){
            httplistener.OnSuccess(buffer.toString());
            }
        } catch (IOException e) {
            httplistener.Onerror(e.getMessage());
        }


    }
    interface HttpConnectionListener{
        void OnSuccess(String result);
        void Onerror(String message);
    }


}
