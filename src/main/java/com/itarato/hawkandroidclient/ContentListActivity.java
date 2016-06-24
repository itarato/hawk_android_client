package com.itarato.hawkandroidclient;

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

import com.itarato.hawkandroidclient.config.AppConfig;
import com.itarato.hawkandroidclient.model.ContentListFeedItem;
import com.itarato.hawkandroidclient.task.ContentListUpdateTask;

import java.util.ArrayList;

public class ContentListActivity extends AppCompatActivity {

    private ContentListAdapter contentListAdapter;

    private static final String LOG_TAG = ContentListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hawkac_content_list_activity);

        ArrayList<ContentListFeedItem> contentList = new ArrayList<>();

        this.contentListAdapter = new ContentListAdapter(this.getApplicationContext(), R.layout.content_list_item, contentList);
        ListView listView = (ListView) this.findViewById(R.id.hawkac_content_list);

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
        if (item.getItemId() == R.id.refresh) {
            refreshContentFeed();
            return true;
        } else if (item.getItemId() == R.id.settings) {
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
