package com.itarato.hawk;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class ContentViewSliderActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view_slider);

        ViewPager viewPager = (ViewPager) findViewById(R.id.content_slider);

        FragmentStatePagerAdapter fragmentStatePagerAdapter = new ContentViewSliderAdapter(getSupportFragmentManager(), this.getIntent().getStringArrayExtra("pages"));
        viewPager.setAdapter(fragmentStatePagerAdapter);
    }

    private static class ContentViewSliderAdapter extends FragmentStatePagerAdapter {

        private final String[] pages;

        public ContentViewSliderAdapter(FragmentManager fm, String[] pages) {
            super(fm);
            this.pages = pages;
        }

        @Override
        public Fragment getItem(int position) {
            ContentViewFragment fragment = new ContentViewFragment();
            fragment.setPage(this.pages[position]);
            return fragment;
        }

        @Override
        public int getCount() {
            return pages.length;
        }

    }

}
