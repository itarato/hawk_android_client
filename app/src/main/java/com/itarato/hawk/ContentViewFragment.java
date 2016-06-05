package com.itarato.hawk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class ContentViewFragment extends Fragment {

    private String page;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        WebView webView = (WebView) inflater.inflate(R.layout.content_view_fragment, container, false);

        webView.loadUrl("file://" + getContext().getFilesDir().getPath() + "/" + this.page);

        return webView;
    }

    public void setPage(String page) {
        this.page = page;
    }

}
