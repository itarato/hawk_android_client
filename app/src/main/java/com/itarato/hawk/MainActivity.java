package com.itarato.hawk;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.itarato.hawk.config.AppConfig;
import com.itarato.hawk.model.ContentListFeedItem;
import com.itarato.hawk.task.ContentListUpdateTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ContentListAdapter contentListAdapter;

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ContentListFeedItem> contentList = new ArrayList<>();

        this.contentListAdapter = new ContentListAdapter(this.getApplicationContext(), R.layout.content_list_item, contentList);
        ListView listView = (ListView) this.findViewById(R.id.content_list);

        assert listView != null;
        listView.setAdapter(this.contentListAdapter);

        BroadcastReceiver broadcastReceiver = new ContentListBroadcastReceiver(this.contentListAdapter);

        IntentFilter intentFilter = new IntentFilter(AppConfig.INTENT_CONTENT);
        this.registerReceiver(broadcastReceiver, intentFilter);

        refreshContentFeed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                refreshContentFeed();
                return true;

            case R.id.settings:
                Intent i = new Intent(this, AppSettingsActivity.class);
                this.startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void refreshContentFeed() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String feedURL = prefs.getString(AppConfig.CONFIG_FEED_URL, null);
        new ContentListUpdateTask(this.contentListAdapter).execute(feedURL);
    }

}
