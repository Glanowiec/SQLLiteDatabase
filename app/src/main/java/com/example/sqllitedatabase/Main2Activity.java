package com.example.sqllitedatabase;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

    //Shows html file placed in assets folder
    WebView myBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myBrowser = (WebView) findViewById(R.id.webview);
        myBrowser.loadUrl("file:///android_asset/authors.html");
    }

}
