package com.dealfaro.luca.listviewexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NewsSites extends AppCompatActivity {
    public final static String LOG_TAG = "";
    WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_sites);
        myWebView = (WebView) findViewById(R.id.webview1);
        //myWebView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myWebView.getSettings();

        String url;
        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            finish(); // No idea what else to do
        } else {
            url = extras.getString("URL");
            Log.d(LOG_TAG, "THIS IS URL: " + url);
            myWebView.loadUrl(url);
        }

    }


}

