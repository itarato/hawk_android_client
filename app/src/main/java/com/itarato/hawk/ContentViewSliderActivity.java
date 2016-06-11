package com.itarato.hawk;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.itarato.hawk.model.ContentManifest;
import com.itarato.hawk.model.ContentPageInfo;
import com.itarato.hawk.util.FileUtil;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;

public class ContentViewSliderActivity extends FragmentActivity {

    ContentManifest manifest = null;

    private static final String LOG_TAG = ContentViewSliderActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view_slider);

        int id = getIntent().getIntExtra("id", 0);
        try {
            this.manifest = ContentManifest.fromJson(FileUtil.JSONObjectFromFilePath(getApplicationContext().getFilesDir().getPath() + "/" + String.valueOf(id) + "/manifest.json"));
        } catch (Exception e) {
            Log.e(LOG_TAG, "Content manifest cannot be open.");
            return;
        }

        ViewPager viewPager = (ViewPager) findViewById(R.id.content_slider);

        FragmentStatePagerAdapter fragmentStatePagerAdapter = new ContentViewSliderAdapter(getSupportFragmentManager(), this.manifest.getPages());
        viewPager.setAdapter(fragmentStatePagerAdapter);
    }

    private static class ContentViewSliderAdapter extends FragmentStatePagerAdapter {

        private final ContentPageInfo[] pages;

        public ContentViewSliderAdapter(FragmentManager fm, ContentPageInfo[] contentPageInfos) {
            super(fm);
            this.pages = contentPageInfos;
        }

        @Override
        public Fragment getItem(int position) {
            ContentViewFragment fragment = new ContentViewFragment();
            fragment.setPage(this.pages[position].getZipPath());
            return fragment;
        }

        @Override
        public int getCount() {
            return pages.length;
        }

    }

}
