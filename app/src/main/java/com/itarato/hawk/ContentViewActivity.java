package com.itarato.hawk;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class ContentViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view);

        int id = getIntent().getIntExtra("id", 0);

        WebView webView = (WebView) findViewById(R.id.content_webview);
        webView.loadUrl("file://"  + getApplicationContext().getFilesDir().getPath() + "/" + String.valueOf(id) + "/index.html");
    }

}
